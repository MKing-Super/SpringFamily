package per.mk.pirate.frame;

import per.mk.pirate.frame.test.controller.TestController;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
监听 9000 接口，对访问的url做出反应
 */
public class HttpServer {

    public Thread start(){
        Thread thread = new Thread(this::portListener);
        thread.setDaemon(false); // 明确设置为非守护线程
        thread.start();
        return thread;
    }

    private void portListener(){
        int port = 9000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(new Date());
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();

                Event event = new Event() {
                    @Override
                    public void handle() {
                        try {
                            handleRequest(clientSocket); // 处理请求
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                Whole.getEventLoop().postEvent(event);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            // 1. 解析请求行
            String requestLine = reader.readLine();
            String[] requestLineSplit = requestLine.split(" ");
            HashMap<String, String> request = new HashMap<>();
            request.put("type",requestLineSplit[0]);
            request.put("url",requestLineSplit[1]);
            request.put("other",requestLineSplit[2]);
            System.out.println("Request Line: " + request);

            // 2. 解析请求头
            Map<String, String> headers = new HashMap<>();
            String headerLine;
            while (true) {
                headerLine = reader.readLine();
                if (headerLine.isEmpty() ){
                    break;
                }
                String[] parts = headerLine.split(": ", 2);
                if (parts.length == 2) {
                    headers.put(parts[0], parts[1]);
                }
            }
            System.out.println("Headers: " + headers);

            // 3. 解析请求体（如有）
            StringBuilder body = new StringBuilder();
            if (headers.containsKey("Content-Length")) {
                int contentLength = Integer.parseInt(headers.get("Content-Length"));
                char[] buffer = new char[contentLength];
                reader.read(buffer, 0, contentLength);
                body.append(buffer);
            }
            System.out.println("Body: " + body);

            System.out.println(">>>>>>>>>> http handle start~");
            Object o = httpHandlerMethod(request);
            System.out.println("<<<<<<<<<< http handle completed.result -> " + o);

            // 4. 返回响应
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println();
            writer.println("Request received.");
            writer.println(o);
        }
    }

    private static Object httpHandlerMethod(Map request){
        String url = request.get("url").toString();
        String[] split = url.split("\\?", 2);
        String urlPre = split[0];
        String params = split.length == 2 ? split[1] : null;

        BeanInfo beanInfo = BeanRegister.beanRegisterMap.get(urlPre);
        if (beanInfo == null){
            return "-------------- other -------------";
        }else {
            Object object = beanInfo.getObject();
            Method method = beanInfo.getMethod();
            Parameter[] parameters = method.getParameters();
//            Class<?>[] types = new Class[parameters.length];
//            for (int j = 0 ; j < parameters.length ; j++) {
//                types[j] = parameters[j].getType();
//                System.out.println("参数类型: " + parameters[j].getType().getName());
//                System.out.println("参数名称: " + parameters[j].getName());
//            }
            Object[] args = {params};
            try {
                Object invoke = method.invoke(object, args);
                return invoke;
            }catch (Exception e){
                e.printStackTrace();
                return "系统异常！！！";
            }
        }

    }

}
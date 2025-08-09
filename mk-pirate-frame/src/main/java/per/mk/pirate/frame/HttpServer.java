package per.mk.pirate.frame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {

    public EventLoop eventLoop;

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
                handleRequest(clientSocket); // 处理请求
//                Event event = new Event() {
//                    @Override
//                    public void handle() {
//                        try {
//
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                eventLoop.postEvent(event);

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
            System.out.println("Request Line: " + requestLine);

            // 2. 解析请求头
            Map<String, String> headers = new HashMap<>();
            String headerLine;
            while (!(headerLine = reader.readLine()).isEmpty()) {
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

            System.out.println("http 请求处理");
//            httpHandlerMethod();
            System.out.println("http 请求处理 完成");

            // 4. 返回响应
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println();
            writer.println("Request received");
        }
    }
}
package com.example.demo.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName demo
 * @Package com.example.demo.socket
 * @Date 2025/8/15 16:33
 * @Version 1.0
 */
public class HttpMain {
    public static void main(String[] args) throws InterruptedException {
        // 指定端口
        int port = 9000;
        // 创建并绑定端口9000的 服务器套接字，但此时尚未开始 监听客户端连接 。
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // 阻塞等待客户端连接，从队列取请求
            Socket socket = serverSocket.accept();
            // 接收客户端数据
            InputStream inputStream = socket.getInputStream();
            // 向客户端发送数据
            OutputStream outputStream = socket.getOutputStream();
            try {
                // 读取请求
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                // 输出到页面
                PrintWriter writer = new PrintWriter(outputStream);

                // 1. 解析请求行
                String requestLine = reader.readLine();
                String[] requestLineSplit = requestLine.split(" ");
                HashMap<String, String> request = new HashMap<>();
                request.put("type",requestLineSplit[0]);
                request.put("url",requestLineSplit[1]);
                request.put("other",requestLineSplit[2]);
                System.out.println("1.请求行：" + request);

                // 2. 解析请求头
                Map<String, String> headers = new HashMap<>();
                String headerLine;
                while (true) {
                    headerLine = reader.readLine();
                    if (headerLine.isEmpty() ){
                        System.out.println("3.空行。");
                        break;
                    }
                    String[] parts = headerLine.split(": ", 2);
                    if (parts.length == 2) {
                        headers.put(parts[0], parts[1]);
                    }
                }
                System.out.println("2.请求头：" + headers);

                // 3. 解析请求体（如有）
                StringBuilder body = new StringBuilder();
                if (headers.containsKey("Content-Length")) {
                    int contentLength = Integer.parseInt(headers.get("Content-Length"));
                    char[] buffer = new char[contentLength];
                    reader.read(buffer, 0, contentLength);
                    body.append(buffer);
                }
                System.out.println("请求体：" + body);

                Object o = "request请求处理完成。" + request.get("url");

                // 4. 返回响应
                System.out.println("返回响应");
                writer.println("HTTP/1.1 200 OK");
                writer.println("Content-Type: text/plain; charset=UTF-8");
                writer.println();
                writer.println("Request received.");
                writer.println(o);
                writer.flush();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                inputStream.close();
                outputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

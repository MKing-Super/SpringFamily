package per.mk.pirate.test.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 客户端
public class SocketMain {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            byte[] data = "自定义消息\n".getBytes();
            OutputStream out = socket.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lynch on 2019-08-21. <br>
 **/
public class IOServer {
    public static void main(String[] args) throws IOException {
        //服务端处理客户端连接请求
        ServerSocket serverSocket = new ServerSocket(3333);
        // 接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理
        new Thread(() -> {
            while (true) {
                try {
                    //阻塞的方法获取新连接
                    Socket socket = serverSocket.accept();
                    //每一个新连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {

                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            //按字节流读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

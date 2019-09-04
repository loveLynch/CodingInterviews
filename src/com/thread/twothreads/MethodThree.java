package com.thread.twothreads;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by lynch on 2019-09-03. <br>
 * 第三种解法，是利用PipedInputStreamAPI；
 * 这里用流在两个线程间通信，但是Java中的Stream是单向的，
 * 所以在两个线程中分别建了一个input和output。
 * 这显然是一种很搓的方式，不过也算是一种通信方式吧
 **/
public class MethodThree {
    private final PipedInputStream inputStream1;
    private final PipedOutputStream outputStream1;
    private final PipedInputStream inputStream2;
    private final PipedOutputStream outputStream2;
    private final byte[] MSG;

    public MethodThree() {
        inputStream1 = new PipedInputStream();
        outputStream1 = new PipedOutputStream();
        inputStream2 = new PipedInputStream();
        outputStream2 = new PipedOutputStream();
        MSG = "Go".getBytes();
        try {
            inputStream1.connect(outputStream2);
            outputStream1.connect(inputStream2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() throws IOException {
        inputStream1.close();
        inputStream2.close();
        outputStream1.close();
        outputStream2.close();
    }

    public Runnable newThreadOne() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;
            private PipedInputStream in = inputStream1;
            private PipedOutputStream out = outputStream1;

            public void run() {
                for (int i = 0; i < arr.length; i = i + 2) {
                    Helper.print(arr[i], arr[i + 1]);
                    try {
                        out.write(MSG);

                        byte[] inArr = new byte[2];
                        in.read(inArr);
//                        System.out.println("t1 " + i + " " + new String(inArr));

                        while (true) {
                            if ("Go".equals(new String(inArr)))
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public Runnable newThreadTwo() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;
            private PipedInputStream in = inputStream2;
            private PipedOutputStream out = outputStream2;

            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    try {
                        byte[] inArr = new byte[2];
                        in.read(inArr);
//                        System.out.println("t2 " + i + " " + new String(inArr));

                        while (true) {
                            if ("Go".equals(new String(inArr)))
                                break;
                        }
                        Helper.print(arr[i]);
                        out.write(MSG);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public static void main(String args[]) throws IOException {
        MethodThree three = new MethodThree();
        Helper.instance.run(three.newThreadOne());
        Helper.instance.run(three.newThreadTwo());
        Helper.instance.shutdown();
        three.shutdown();
    }
}
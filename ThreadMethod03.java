package com.jtl.threaduse;

public class ThreadMethod03 {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        myDaemonThread.setDaemon(true);//设置为守护进程
        myDaemonThread.start();
        //如果我们希望当main线程结束后，子线程可以自动退出
        //只需将子线程设为 守护线程 即可
        for (int i = 1; i <= 10; i++) {
            System.out.println("小黄在辛苦的学习...");
            System.out.println("小黄在辛苦的学习...");
            System.out.println("小黄在辛苦的学习...");
            System.out.println("小黄在辛苦的学习...");
            Thread.sleep(1000);
        }
    }
}

class MyDaemonThread extends Thread {//Daemon 守护进程

    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("小明和小红踢足球");
        }

    }
}

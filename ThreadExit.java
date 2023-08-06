package com.jtl.threadexit_;

import java.util.Scanner;

/**
 * @author jtl
 * java学习用
 */
public class ThreadExit{
    public static void main(String[] args) {
        T t = new T();
        t.start();
        //如果希望main线程去控制t线程的终止，必须可以修改loop
        //让t退出run方法，从而终止t线程 -》通知方式

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.setLoop(false);

    }
}

class T extends Thread{
    boolean loop = true;
    int count;
    @Override
    public void run() {
        while(loop){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T运行中......" + (++count));
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}

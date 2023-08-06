package com.jtl.Homework;

import java.util.Scanner;

/**
 * @author jtl
 * java学习用
 * //通知方式 B线程控制A线程
 */
public class Homework01 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        new Thread(a).start();
        new Thread(b).start();
    }
}

class A implements Runnable {

    private boolean flag = true;

    @Override
    public synchronized void run() {
        while (flag) {
            System.out.println((int) (1 + Math.random() * 100));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class B implements Runnable {
    private A a;

    public B(A a) {
        this.a = a;
    }

    @Override
    public synchronized void run() {
        Scanner scanner = new Scanner(System.in);
        char ch = ' ';
        while (true) {
            ch = scanner.next().charAt(0);
            if (ch == 'Q') {
                a.setFlag(false);
                System.out.println("B线程退出");
                break;
            }
        }
    }

}

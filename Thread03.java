package com.jtl.threaduse;

/**
 * @author jtl
 * java学习用
 * 创建两个线程去干两件事
 */
public class Thread03 {
    public static void main(String[] args) {
        A a = new A();
        Thread thread = new Thread(a);
        B b = new B();
        Thread thread1 = new Thread(b);
        thread.start();//启动第一个线程
        thread1.start();//启动第二个线程
    }
}

class A implements Runnable{
    int count;
    @Override
    public void run() {
        while(true){
            System.out.println("hello world" + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 10){
                System.out.println(Thread.currentThread().getName() + "结束了！！！！！");
                break;
            }
        }
    }
}

class B implements  Runnable{
    int count;
    @Override
    public void run() {
        while(true){
            System.out.println("hi" + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count == 5){
                System.out.println(Thread.currentThread().getName() + "结束了！！！！！");
                break;
            }
        }
    }
}

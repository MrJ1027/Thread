package com.jtl.threaduse;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

/**
 * @author jtl
 * java学习用
 *
 * 通过继承Thread类创建线程
 */
public class Thread01 {
    public static void main(String[] args) {
        //创建一个Cat对象，可以当做线程使用
        Cat cat = new Cat();
        cat.start();//启动线程 --> 不能直接cat.run()方法，因为run方法就是一个普通的方法，并没有真正启动一个线程，而会阻塞在这
        //等待run方法执行完毕后才向下执行
        //看底层 执行start() ---> 再执行 start0()这个方法时真正实现多线程的方法，它内部调用run
        // 当main线程启动一个子线程Thread-0，主线程不会阻塞，会继续执行
        //这时，主线程和子线程交替执行
        for(int i = 0; i < 10; i++){
            System.out.println("主线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//1.当一个类继承了Thread类，该类就可以当做线程使用
//2.重写run方法，写上自己的逻辑业务
//3.run Thread类实现了 Runnable接口的run方法
/*
   @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }
 */

class Cat extends Thread{
    int count;
    @Override
    public void run() {//重写run方法
        //该线程每间隔1s，输出一句话 ctrl + alt + t
        while(true) {
            System.out.println("小猫喵喵叫" + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

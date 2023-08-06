package com.jtl.threaduse;

/**
 * @author jtl
 * java学习用
 * 主线程先干一半，然后插入子线程全部做完，再做完剩下的主线程
 */
public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {
//        SonThread sonThread = new SonThread();
//        Thread thread = new Thread(sonThread);
        Thread thread = new Thread(new SonThread());
        for(int i = 1; i <= 10; i++){
            System.out.println("hi" + i);
            Thread.sleep(1000);
            if(i == 5){
                thread.start();//启动子线程，输出hello
                thread.join();//立即将thread子线程插入到main，让thread先执行
            }

        }
        System.out.println("主线程结束");
    }
}

class SonThread implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i <= 10; i++){
            System.out.println("hello" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("子线程结束");
    }
}

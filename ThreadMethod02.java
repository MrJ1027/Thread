package com.jtl.threaduse;

/**
 * @author jtl
 * java学习用
 * join方法
 */
public class ThreadMethod02 {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        for (int i = 1; i <= 20; i++) {
            Thread.sleep(1000);
            if(i == 5){
                System.out.println("主线程让子线程插队...");
                //t.join();//线程插队 (调用插队方的join方法，先执行完插入的线程再往下)
                //Thread.yield();//礼让，不一定成功
                System.out.println("子线程吃完，主线程继续吃");
            }
            System.out.println("主线程 吃了" + i + "个包子");

        }
    }
}

class T extends Thread{
    @Override
    public void run() {
        for(int i = 1; i <= 20; i++){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("子线程 吃了" + i + "个包子");
        }
    }
}

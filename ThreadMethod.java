package com.jtl.threaduse;



/**
 * @author jtl
 * java学习用
 */
public class ThreadMethod {
    public static void main(String[] args) throws InterruptedException {
        TT t = new TT();
        t.setName("jtl");
        t.setPriority(Thread.MIN_PRIORITY);//最小优先级
        t.start();
        //主线程打印五个消化完了，就中断子线程的休眠
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("消化完了" + i);
        }
        System.out.println(t.getName() + "线程的优先级：" + t.getPriority());
        t.interrupt();//当执行到这里，就会中断t线程的休眠


    }
}

class TT extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "吃饭..." + i);
            }
            try {
                System.out.println("休眠中..........");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                //当该线程执行到一个interrupt方法时，就会catch一个异常，可以加入自己的逻辑
                //InterruptedException 是捕获一个中断异常
                System.out.println(Thread.currentThread().getName() + "被 interrupt了");
            }

        }
    }
}

package com.jtl.Homework;

/**
 * @author jtl
 * java学习用
 */
public class Homework02 {
    public static void main(String[] args) {
        Card card = new Card();
        Thread thread1 = new Thread(card);
        thread1.setName("t1");
        Thread thread2 = new Thread(card);
        thread2.setName("t2");
        thread1.start();
        thread2.start();
    }
}

//涉及到多个线程共享资源，所以我们使用实现Runnable方式
class Card implements Runnable {
    private int money = 10000;

    @Override
    public void run() {
        //这里使用synchronized实现了线程同步
        //当多个线程执行到这里时，就会去争夺this对象锁
        //哪个线程获取到this对象锁，就执行synchronized代码块
        //争夺不到this对象锁，就blocked，准备继续争夺
        //发现同步代码块包含while循环则t2抢不到锁
        while (true) {
            synchronized (this) {
                if (money < 1000) {
                    System.out.println("余额小于1000，无法取款！！！");
                    break;
                }
                money -= 1000;
                System.out.println(Thread.currentThread().getName() + "取款成功,余额：" + money);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

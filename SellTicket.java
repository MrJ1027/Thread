package com.jtl.syn;

/**
 * @author jtl
 * java学习用
 * 使用多线程模拟三个窗口售票 --> 超卖
 */
public class SellTicket {
    public static void main(String[] args) {
//        SellTicket1 sellTicket1 = new SellTicket1();
//        new Thread(sellTicket1).start();
//        new Thread(sellTicket1).start();
//        new Thread(sellTicket1).start();



//        SellTicket2 sellTicket1 = new SellTicket2();
//        SellTicket2 sellTicket2 = new SellTicket2();
//        SellTicket2 sellTicket3 = new SellTicket2();
//        sellTicket1.start();
//        sellTicket2.start();
//        sellTicket3.start();

        SellTicket3 sellTicket3 = new SellTicket3();
        new Thread(sellTicket3).start();
        new Thread(sellTicket3).start();
        new Thread(sellTicket3).start();

    }
}

//实现接口方式，使用synchronized实现线程同步
class SellTicket3 implements Runnable{
    int ticketCount = 100;
    boolean loop = true;
    Object obj = new Object();

    //1.public synchronized void m1()的锁是加在SellTicket03.class上
    //1.如果在静态方法中，实现一个同步代码块
    /*
      public synchronized void m2(){
        synchronized(SellTicket3.class){
            System.out.println("m2");
        }
     }
    * */
    public synchronized void m1(){

    }
    public synchronized void m2(){
        synchronized(SellTicket3.class){
            System.out.println("m2");
        }
    }


    //public synchronized void sell()是同步方法，在同一时刻，只能有一个线程来执行sell方法
    //这时侯锁在this对象，也可以在代码块上写synchronized，同步代码块,互斥锁还是在this对象
    //一个线程做完了之后，下个线程才进来做，不会造成多个线程同时判断ticketCount而导致冲突
    public /*synchronized*/ void sell(){

        synchronized (obj/*this */) {
            if (ticketCount <= 0) {
                System.out.println("售票结束.....");
                loop = false;
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "出售了一张票，还剩： " + (--ticketCount) + " 张票");
        }

    }

    @Override
    public void run() {
        while(loop){
            sell();//sell方法是一个同步方法
        }
    }
}



//实现Runnable接口创建线程
class SellTicket1 implements Runnable{
    int ticketCount = 100;
    @Override
    public void run() {
        while(true){
            if(ticketCount <= 0){
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "出售了一张票，还剩： " + (--ticketCount) + " 张票");
        }
    }
}

//继承Thread类创建线程
class SellTicket2 extends Thread{
    static int ticketCount = 100;
    @Override
    public void run() {
        while(true){
            if(ticketCount <= 0){
                System.out.println(Thread.currentThread().getName() + "售票结束");
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "出售了一张票，还剩： " + (--ticketCount) + " 张票");
        }
    }
}

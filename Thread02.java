package com.jtl.threaduse;

/**
 * @author jtl
 * java学习用
 * 通过实现Runnable接口 来开发线程
 */
public class Thread02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //dog.start(); error 不能调用start
        //创建了Thread对象，把dog对象(实现了Runnable)放入Thread --> 代理模式
        Thread thread = new Thread(dog);
        thread.start();

        Tiger tiger = new Tiger();//实现了Runnable接口
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();



    }
}

class Animal{}
class Tiger extends Animal implements  Runnable{

    @Override
    public void run() {
        System.out.println("老虎嗷嗷叫");
    }
}

//线程代理类 让别人买火车票，最后火车票还是我的
class ThreadProxy implements  Runnable{

    private Runnable target = null;
    @Override
    public void run() {
        if(target != null){
            target.run();//动态绑定 ---> 老虎嗷嗷叫
        }
    }

    public ThreadProxy(Runnable target){
        this.target = target;
    }

    public void start(){
        start0();//这个方法真正实现多线程方法
    }

    public void start0(){
        run();
    }
}

class Dog implements Runnable{
//通过实现Runnable接口 来开发线
    int count;
    @Override
    public void run() {
        while(true){
            System.out.println("小狗汪汪叫" + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 10){
                break;
            }
        }
    }
}

package com.jtl.syn;

/**
 * @author jtl
 * java学习用
 * 模拟线程死锁
 */
public class DeadLock_ {
    public static void main(String[] args) {
        //模拟死锁
        DeadLockDemo A = new DeadLockDemo(true);
        DeadLockDemo B = new DeadLockDemo(false);
        A.start();
        B.start();
        //run：E:\program\Java\bin\java.exe "-javaagent:E:\program\idea\IntelliJ IDEA 2022.1.4\lib\idea_rt.jar=54640:E:\program\idea\IntelliJ IDEA 2022.1.4\bin" -Dfile.encoding=UTF-8 -classpath E:\program\Java\jre\lib\charsets.jar;E:\program\Java\jre\lib\deploy.jar;E:\program\Java\jre\lib\ext\access-bridge-64.jar;E:\program\Java\jre\lib\ext\cldrdata.jar;E:\program\Java\jre\lib\ext\dnsns.jar;E:\program\Java\jre\lib\ext\jaccess.jar;E:\program\Java\jre\lib\ext\jfxrt.jar;E:\program\Java\jre\lib\ext\localedata.jar;E:\program\Java\jre\lib\ext\nashorn.jar;E:\program\Java\jre\lib\ext\sunec.jar;E:\program\Java\jre\lib\ext\sunjce_provider.jar;E:\program\Java\jre\lib\ext\sunmscapi.jar;E:\program\Java\jre\lib\ext\sunpkcs11.jar;E:\program\Java\jre\lib\ext\zipfs.jar;E:\program\Java\jre\lib\javaws.jar;E:\program\Java\jre\lib\jce.jar;E:\program\Java\jre\lib\jfr.jar;E:\program\Java\jre\lib\jfxswt.jar;E:\program\Java\jre\lib\jsse.jar;E:\program\Java\jre\lib\management-agent.jar;E:\program\Java\jre\lib\plugin.jar;E:\program\Java\jre\lib\resources.jar;E:\program\Java\jre\lib\rt.jar;E:\program\IDEACode\chapter17\out\production\chapter17 com.jtl.syn.DeadLock_
        //Thread-1进入3
        //Thread-0进入1
        //要避免死锁，不要去抢锁
    }
}

class DeadLockDemo extends Thread {
    static Object o1 = new Object();
    static Object o2 = new Object();
    boolean flag;

    public DeadLockDemo(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        //下面业务逻辑分析：
        //1.如果flag为真，线程A就会先得到/持有 o1 对象锁，然后尝试去获取o2对象锁
        //2.如果线程A得不到o2对象锁，就会Blocked
        //3.假设flag为假，线程B就会先得到/持有 o2 对象锁，然后尝试去获取o1对象锁
        //4.如果线程B得不到o1对象锁，就会Blocked
        if (flag) {
            synchronized (o1){//对象互斥锁，下面是同步代码块
                System.out.println(Thread.currentThread().getName() + "进入1");
                synchronized(o2){
                    System.out.println(Thread.currentThread().getName() + "进入2");
                }
            }
        }else{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "进入3");
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + "进入4");

                }
            }
        }
    }
}

package com.jtl.threaduse;

/**
 * @author jtl
 * java学习用
 */
public class CupNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cpuNums = runtime.availableProcessors();
        System.out.println("cpuNums = " + cpuNums);
    }
}

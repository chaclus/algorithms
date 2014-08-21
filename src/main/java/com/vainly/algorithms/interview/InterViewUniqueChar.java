package com.vainly.algorithms.interview;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 判断一个字符串中的字符是否唯一
 *
 * http://www.cricode.com/254.html
 *
 * Created by chaclus on 2014/8/20.
 */
public class InterViewUniqueChar {
    @Test
    public void test1() {
        String str1 = "abcdefg";
        String str2 = "abcdefga";

        System.out.println("str1 is " + this.isUnique1(str1) + " , str2 is " + this.isUnique1(str2));
    }


    @Test
    public void test2() {
        String str1 = "abcdefg";
        String str2 = "abcdefga";

        System.out.println("str1 is " + this.isUnique2(str1) + " , str2 is " + this.isUnique2(str2));
    }


    @Test
    public void test3() {
        System.out.println("---------TEST3----------");
        for (int i = 0; i < 1000000; i++) {
            int m = random(1,2147480648);
            int n = random(0, 32);
//            System.out.println("m=" + m +"  n = "+n+ " , m<<n : "+(m<<n) +" "+ left(m,n)+", m>>n : "+(m>>n) +" "+ right(m,n));

            if((m<<n)!= left(m,n)){
                System.out.println("m=" + m +"  n = "+n+ " , m<<n : "+(m<<n) +" "+ left(m,n));
            }

//            if((m>>n)!= right(m,n)){
//                System.out.println("m=" + m +"  n = "+n+ " , m>>n : "+(m<<n) +" "+ right(m,n));
//            }
        }
    }


    public void test4() {
        System.out.println("---------TEST4----------");
        for (int i = 0; i < 100; i++) {
            int m = random(1,2147480648);
            System.out.println("m = "+m+" , m<<31 = "+(m << 31));
//            System.out.println("m = "+m+" , m<<32 = "+(m << 32));
        }
    }


    @Test
    public void test5() {
        System.out.println("---------TEST5----------");
        int checker = 0;
        int val = 3;
        System.out.println("1<<val  " + (1 << val));
        System.out.println("checker & (1 << val)  " + (checker & (1 << val)));

        checker |= (1<<val);
        System.out.println("checker "+checker);

        val = 4;
        System.out.println("1<<val  " + (1 << val));
        System.out.println("checker & (1 << val)  " + (checker & (1 << val)));
        checker = (1<<val) | checker;
        System.out.println("checker "+checker);

        val = 5;
        System.out.println("1<<val  " + (1 << val));
        System.out.println("checker & (1 << val)  " + (checker & (1 << val)));
        checker = (1<<val) | checker;
        System.out.println("checker "+checker);

        val = 4;
        System.out.println("1<<val  " + (1 << val));
        System.out.println("checker & (1 << val)  " + (checker & (1 << val)));
        checker = (1<<val) | checker;
        System.out.println("checker "+checker);
    }



    //整数左移
    public int left(int m, int n) {
        if (n < 31) {

            return m * (int) Math.pow(2, n);
        }else if (n == 31) {
            if (m % 2 == 0) {
                return 0;
            } else {
                return -2147483648;
            }
        }else {
            return m * (int) Math.pow(2, (n - 31));
        }
    }

    //整数右移
    public int right(int m, int n) {
        if (n < 31) {
            return m / (int)Math.pow(2, n);
        }else if (n == 31) {
            return m;
        }else{
            return m / (int) Math.pow(2, (n - 31));
        }
    }

    /**
     * 构成字符串的字符集为ASCII字符串
     */
    public boolean isUnique1(String str) {
        Boolean[] a = new Boolean[256];
        //为字符串赋初始值
        Arrays.fill(a, false);

        for (int i = 0; i < str.length(); i++) {
            int index = (int) str.charAt(i);
            if (a[index]) {
                return false;
            }
            a[index] = true;
        }
        return true;
    }

    public boolean isUnique2(String str) {
        int[] a = new int[8];

        Arrays.fill(a, 0);
        for (int i = 0; i < str.length(); i++) {
            int v = (int) str.charAt(i);

            int idx = v/32 ,shift = v%32;
            if ((a[idx] & (1 << shift))>0) {
                return false;
            }
            a[idx] |= (1 << shift);
        }

        return true;
    }


    //只判断字符串 a-z A-Z
    //只要字符串重复，则一直在checker的二进制上填充‘1’，若重复 求 &时就>0了
    public boolean isUnique3(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val))>0) {
                return false;
            }
            checker |= (1 << val);
        }

        return true;
    }

    public int  random(int sr, int factor) {
        return Math.abs(new Random().nextInt(factor)%(factor-sr+1)+sr);
    }
}

package pers.mk.interview.algorithm.base;

import java.util.Arrays;
import java.util.HashMap;

public class ArrayAndString {
    public static void main(String[] args) {
        // 数组
//        method1();//数组声明与初始化
//        method2();//数组基本操作
//        method3();//数组遍历方法
//        method4();//常用API（java.util.Arrays）

        // 字符串
//        method5();//字符串创建
//        method6();//字符串基本操作
//        method7();//字符串修改操作
//        method8();//字符串遍历
//        method9();//字符串与数组转换
//        method10();//性能优化：StringBuilder和StringBuffer

        // 实践：
//        method11();// 反转数组
//        method12();//查找最大值
//        method13();// 是否是回文字符串
        method14();// 统计字符出现次数

    }

    // 统计字符出现次数
    private static void method14(){
        String str = "Hello World";
        HashMap<String, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char c : chars){
            if (map.get(String.valueOf(c)) == null){
                map.put(String.valueOf(c),1);
            }else {
                map.put(String.valueOf(c),map.get(String.valueOf(c)) + 1);
            }
        }
        System.out.println(map.toString());
    }

    // 是否是回文字符串
    private static void method13(){
//        String str = "Hello";
        String str = "1234321";
        int left = 0;
        int right = str.length() - 1;
        while (left < right){
            if (str.charAt(left) != str.charAt(right)){
                System.out.println("不是回文字符串！");
                break;
            }
            left++;
            right--;
        }
    }

    //查找最大值
    private static void method12(){
        int[] arr = {56,23,89,91,23,14};
        int max  = arr[0];
        for (int num : arr){
            if (num > max){
                max = num;
            }
        }
        System.out.println(max);
    }

    // 反转数组
    private static void method11(){
        String str = "Hello";
        char[] arr = str.toCharArray();

        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.println(new String(arr));
    }

    //性能优化：StringBuilder和StringBuffer
    private static void method10(){
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Hello").append(" ").append("World");
        System.out.println(sb1);
        sb1.insert(5,",");
        System.out.println(sb1);
        sb1.delete(5,6);
        System.out.println(sb1);
        sb1.reverse();
        System.out.println(sb1);

        System.out.println("-----------------------------------");

        StringBuffer sb2 = new StringBuffer();
        sb2.append(" Programming");
        System.out.println(sb2);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 1000 ; i++){
            sb.append(i);
        }
        System.out.println(sb);

    }

    //字符串与数组转换
    private static void method9(){
        String str = "Hello";
        char[] charArray = str.toCharArray();
        System.out.println(Arrays.toString(charArray));

        byte[] byteArray = str.getBytes();
        System.out.println(Arrays.toString(byteArray));

        String[] strArray = str.split("");
        System.out.println(Arrays.toString(strArray));

        char[] chars = {'J', 'a', 'v', 'a'};
        System.out.println(new String(chars));
        System.out.println(String.valueOf(chars));
        System.out.println(String.join("","J","a","v","a"));
        System.out.println(String.join("-","J","a","v","a"));


    }

    //字符串遍历
    private static void method8(){
        String str = "Hello";

        for (int i = 0 ; i < str.length() ; i++){
            char ch = str.charAt(i);
            System.out.println(ch);
        }
        System.out.println("--------------------------------");

        char[] chars = str.toCharArray();
        for (char ch : chars){
            System.out.println(ch);
        }

    }

    //字符串修改操作
    private static void method7(){
        String original = " Hello,World! ";
        System.out.println(original);
        String trimed = original.trim();
        System.out.println(trimed);

        System.out.println(original.toUpperCase());
        System.out.println(original.toLowerCase());

        String replaced1 = original.replace('l', 'L');
        System.out.println(replaced1);
        String replaced2 = original.replace("World", "Java");
        System.out.println(replaced2);

        String csv = "Java,Python,C++,JavaScript";
        String[] langs = csv.split(",");
        System.out.println(Arrays.toString(langs));
        String joined = String.join("-", langs);
        System.out.println(joined);

        // java 11+
//        String repeated = "Java".repeat(3);
//        System.out.println(repeated);
    }

    //字符串基本操作
    private static void method6(){
        String str = "Java Programming";

        int len = str.length();
        System.out.println(len);

        System.out.println(str.charAt(0));

        String sub1 = str.substring(5);
        System.out.println(sub1);

        String sub2 = str.substring(5, 11);
        System.out.println(sub2);

        int index1 = str.indexOf('a');
        System.out.println(index1);
        int index2 = str.indexOf("Pro");
        System.out.println(index2);
        int lastIndex = str.lastIndexOf('a');
        System.out.println(lastIndex);
        boolean contains = str.contains("Java");
        System.out.println(contains);
        boolean starts = str.startsWith("Java");
        System.out.println(starts);
        boolean ends = str.endsWith("ing");
        System.out.println(ends);

        System.out.println("----------------------------");
        String a = "Java";
        String b = "java";
        System.out.println(a.equals(b));
        System.out.println(a.equalsIgnoreCase(b));
        int compare = a.compareTo("Python");
        System.out.println(compare);
    }

    //字符串创建
    private static void method5(){
        String str1 = "hello";
        String str1_1 = "hello";
        String str2 = new String("hello");
        System.out.println(str1 == str1_1);
        System.out.println(str1.equals(str2));
        System.out.println(str1 == str2);
        System.out.println("---------------------------");

        char[] chars = {'h','e','l','l','o'};
        String str3 = new String(chars);
        System.out.println(str3);
        String str4 = String.valueOf(123);
        System.out.println(str4);

    }

    //常用API（java.util.Arrays）
    private static void method4(){
        int[] arr = {5,2,8,1,9};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("----------------------------------");
        // 找出指定元素的索引值
        int index = Arrays.binarySearch(arr, 5);
        System.out.println(index);
        System.out.println("--------------------------------------");
        // 填充数组
        int[] filled = new int[5];
        Arrays.fill(filled,7);
        System.out.println(Arrays.toString(filled));
        System.out.println("----------------------------------------------");

        int[] a = {1,2,3};
        int[] b = {1,2,new Integer("3")};
        System.out.println(Arrays.equals(a,b));
        System.out.println("--------------------------------------------");

        int[] copy1 = Arrays.copyOf(arr, 3);
        int[] copy2 = Arrays.copyOfRange(arr, 1, 4);
        System.out.println(Arrays.toString(copy1));
        System.out.println(Arrays.toString(copy2));
        System.out.println("-------------------------------");

    }

    //数组遍历方法
    private static void method3(){
        int[] arr = {1,2,3,4,5};
        for (int i = 0 ; i < arr.length ; i++){
            System.out.println(arr[i]);
        }
        System.out.println("-------------------------------------------------");
        for (int num : arr){
            System.out.println(num + "");
        }
        System.out.println("-------------------------------------------------");
        String s = Arrays.toString(arr);
        System.out.println(s);
        System.out.println("-------------------------------------------------");
    }

    //数组基本操作
    private static void method2(){
        int[] nums = {10,20,30,40,50};
        System.out.println(nums[0]);
        System.out.println(nums.length);

        int[][] martix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        System.out.println(martix[1][0]);
        System.out.println("-------------------------------------------------");
    }

    //数组声明与初始化
    private static void method1(){
        int[] arr1;
        int arr2[];

        int[] arr3 = {1,2,3};
        int[] arr4 = new int[5];
        int[] arr5 = new int[]{1,2,3,4,5};
        String[] strArr = {"111","222"};
    }
}

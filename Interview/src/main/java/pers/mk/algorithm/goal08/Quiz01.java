package pers.mk.algorithm.goal08;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Quiz01 {
    public static void main(String[] args) {
        // 思路不对！！！
//        mk();

        m1();
    }


    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0 ; i < strArr.length ; i++){
            list.add(Integer.parseInt(strArr[i])) ;
            set.add(Integer.parseInt(strArr[i]));
        }
        list.sort((a , b) -> {
            return b.compareTo(a);
        });
//        System.out.println(JSON.toJSONString(set));
//        System.out.println(JSON.toJSONString(list));

        int maxLen = list.get(0);
        int maxHeight = 0;
        boolean pass = true;
        boolean[] arr = new boolean[list.size()];

        for (int i = 0 ; i < list.size() ; i++){
            if (list.get(i) == maxLen && arr[i] == false){
                maxHeight += 1;
                arr[i] = true;
            }else if (list.get(i) < maxLen && arr[i] == false){
                for (int j = 0 ; j < list.size() ; j++){
                    if (arr[j] == false && i != j && list.get(i) + list.get(j) == maxLen){
                        maxHeight += 1;
                        arr[i] = true;
                        arr[j] = true;
                        break;
                    }
                }
            }
        }

        for (int i = 0 ; i < arr.length ; i++){
            if (arr[i] == false){
                pass = false;
                break;
            }
        }

        if (pass){
            System.out.println(maxHeight);
        }else {
            System.out.println(-1);
        }


    }




    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] s = in.nextLine().trim().split("\\s+");
        int[] a = new int[s.length];
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(s[i]);
            sum += a[i];
        }
        Arrays.sort(a);
        int mx = a[a.length - 1];
        int mn = a[0];

        // 层长 L 范围：[mx, mx + mn]
        for (int L = mx; L <= mx + mn; L++) {
            if (sum % L != 0) continue;
            if (can(a, L)) {
                System.out.println(sum / L);
                return;
            }
        }
        System.out.println(-1);
    }

    // 判断能否把所有积木分成若干组，每组和=L，每组1个或2个
    private static boolean can(int[] a, int L) {
        int i = a.length - 1; // 最大
        int j = 0;            // 最小
        while (i >= j) {
            if (a[i] == L) {
                i--;
            } else if (a[i] + a[j] == L) {
                i--;
                j++;
            } else if (a[i] + a[j] < L) {
                return false; // 最小积木凑不上
            } else { // a[i]+a[j] > L
                return false; // 最大积木没法拼
            }
        }
        return true;
    }



}

package pers.mk.interview.jzcw.algorithm;


import java.util.HashMap;
import java.util.LinkedList;

// 20. 有效的括号
public class Quiz13 {

    public static void main(String[] args) {
        // 栈
//        System.out.println(mk("]"));

        System.out.println(m1("[]"));
    }

    private static boolean mk(String s) {
        LinkedList<Character> list = new LinkedList<Character>();
        for (int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if (c == '(' ||c == '{' || c == '['){
                list.addFirst(c);
                continue;
            }
            if (c == ')' ||c == '}' || c == ']'){
                if (list.isEmpty()){
                    return false;
                }
                Character pop = list.pop();
                if (pop == null){
                    return false;
                }else if (pop == '('){
                    if (c != ')'){
                        return false;
                    }
                }else if (pop == '{'){
                    if (c != '}'){
                        return false;
                    }
                }else if (pop == '['){
                    if (c != ']'){
                        return false;
                    }
                }

            }else {
                return false;
            }
        }
        if (list.isEmpty()){
            return true;
        }else {
            return false;
        }

    }


    private static boolean m1(String s){
        int n = s.length();
        if (n % 2 == 1){
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        LinkedList<Character> queue = new LinkedList<>();
        for (int i = 0 ;i < s.length() ; i++){
            char ch = s.charAt(i);
            if (map.containsKey(ch)){
                if (queue.isEmpty() || queue.peek() != map.get(ch)){
                    return false;
                }
                queue.pop();
            }else {
                queue.push(ch);
            }
        }
        return queue.isEmpty();
    }
}

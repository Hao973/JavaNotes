package hao.learn;

import java.util.*;

public class DemoTest {
    static void getNumber(){
        int[] iarr = {1, 2, 2, 1, 3, 3, 6};
        int ret = 0;
        for(int i: iarr){
//            System.out.println(i);
            ret ^= i;
        }
        System.out.println(ret);
    }
    static void twoNums(){
        int[] arr = {1, 2, 7, 3, 11};
        int target = 13;
        Map<Integer, Integer> hashTable = new HashMap<Integer, Integer>();
        for(int i=0; i<arr.length; ++i){
            System.out.println(arr[i]);
            if(hashTable.containsKey(target - arr[i])){
                System.out.println("find: " + hashTable.get(target - arr[i]) + ", " + i);
                break;
            }
            hashTable.put(arr[i], i);
        }
    }

    static int fib0(int n){
        if (0 == n) {
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        return fib0(n-1) + fib0(n-2);
    }

    static int fib1(int n){
        int[] arr = new int[n+1];
        if (0 == n) {
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        arr[1] = 1;
        arr[2] = 1;
        return helper(n-1, arr) + helper(n-2, arr);
    }

    static int helper(int n, int[] arr){
        if(0 != arr[n]){
            return arr[n];
        }
        int ret = helper(n-1, arr) + helper(n-2, arr);
        arr[n] = ret;
        return ret;
    }

    static int fib2(int n){
        int[]  arr = new int[n+1];
        if(1 == n || 2 == n){
            return 1;
        }
        arr[1] = 1;
        arr[2] = 1;
        for(int i=3; i<=n; i++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }

    static int fib3(int n){
        if(1 == n || 2 == n){
            return 1;
        }
        int pre1 = 1;
        int pre2 = 1;
        int ret = 0;
        for(int i=3; i<=n; i++){
            ret = pre1 + pre2;
            pre1 = pre2;
            pre2 = ret;
        }
        return ret;
    }

    public static void fibDemo(){
        System.out.println("fib0: " + fib1(22));
        System.out.println("fib1: " + fib1(22));
        System.out.println("fib2: " + fib2(22));
        System.out.println("fib3: " + fib3(22));
    }

    public static int binarySearch(int[] data, int target){
        int low = 0;
        int high = data.length - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            int ret = data[mid];
            if(ret == target){
                return mid;
            }else if(ret < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
//            System.out.println("low: " + low + ", high: " + high);
        }
//        System.out.println("--------------------------------");
        return -1;
    }

    public static void findDemo(){
        int[] data = {1, 2, 3, 4, 6, 7, 8, 9};
        int[] testData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10};
        for(int i: testData){
            System.out.println("i: " + i + " " + binarySearch(data, i));
        }
    }

    public static void sortDemo(){
        int[] data = {1, 9, 2, 1, 1, 2, 8, 0, 9, 1, 9, 2, 2, 8};
    }

    public static List<List<Integer>> getStringStr(String s){
        List<List<Integer>> ret = new ArrayList<>();
        int n = s.length();
        int num = 1;
        for(int i=0; i<n; i++){
            if(i == n-1 || s.charAt(i) != s.charAt(i+1)){
                if(3 <= num){
                    ret.add(Arrays.asList(i-num+1, i));
                }
                num = 1;
            }else{
                num++;
            }
        }
        return ret;
    }

    public static void stringDemo(){
        System.out.println(getStringStr("aabbbdssssdddaaa"));
    }

    public static void main(String[] args) {
//        fibDemo();
//        stringDemo();
        findDemo();
    }
}

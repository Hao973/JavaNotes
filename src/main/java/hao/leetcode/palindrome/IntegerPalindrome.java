package hao.leetcode.palindrome;

public class IntegerPalindrome {
    public static boolean integerPalindrome(int x){
        if(x < 0){
            return false;
        }
        if(x < 10){
            return true;
        }
        int ilen = 1;
        int tx = x/10;
        while(tx != 0){
            ilen *= 10;
            tx /= 10;
        }
        System.out.println("ilen:" + ilen);
        while(x != 0){
            int left = x / ilen;
            int right = x  % 10;
            if(left != right){
                return false;
            }
            x = (x % ilen) / 10;
            ilen /= 100;
            System.out.println("x:" + x + ", ilen:" + ilen);
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(integerPalindrome(121012));
    }
}

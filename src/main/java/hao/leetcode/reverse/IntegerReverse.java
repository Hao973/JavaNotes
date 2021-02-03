package hao.leetcode.reverse;

public class IntegerReverse {
    public static int reverse(int x){
        // 123
        int ret = 0;
        while(x != 0){
            int pop = x % 10;
            if((ret > Integer.MAX_VALUE/10) || (ret == Integer.MAX_VALUE/10 && pop > Integer.MAX_VALUE%10)){
                return 0;
            }
            if((ret < Integer.MIN_VALUE/10) || (ret == Integer.MIN_VALUE/10 && pop < Integer.MIN_VALUE%10)){
                return 0;
            }
            ret = ret * 10 + pop;
            x /= 10;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1231212));
    }
}

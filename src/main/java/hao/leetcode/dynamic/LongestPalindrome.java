package hao.leetcode.dynamic;

public class LongestPalindrome {
    public static String longestPalindrome(String s){
        int len = s.length();
        if(2 > len){
            return s;
        }
        int begin = 0;
        int maxLen = 1;
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();
        for(int i=0; i<len; i++){
            dp[i][i] = true;
        }
        for(int j=1; j<len; j++){
            for(int i=0; i<j; i++){
                if(charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                }else{
                    if(j-i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if(dp[i][j] && (j-i+1)>maxLen){
                    begin = i;
                    maxLen = j-i+1;
                }
            }
        }
        return s.substring(begin, begin+maxLen);
    }

    public static void main(String[] args) {
        String test = "abcdaadc";
        System.out.println(longestPalindrome(test));

    }
}

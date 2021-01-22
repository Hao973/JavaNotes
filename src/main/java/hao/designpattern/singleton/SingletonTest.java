package hao.designpattern.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        Singleton1 s10 = Singleton1.getInstance();
        Singleton1 s11 = Singleton1.getInstance();
        System.out.println("s10==s11: " + (s10 == s11));

        Singleton2 s20 = Singleton2.getInstance();
        Singleton2 s21 = Singleton2.getInstance();
        System.out.println("s20==s21: " + (s20 == s21));


        Singleton3 s30 = Singleton3.getInstance();
        Singleton3 s31 = Singleton3.getInstance();
        System.out.println("s30==s31: " + (s30 == s31));
    }
}

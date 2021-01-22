package hao.designpattern.singleton;

public class Singleton2 {
    private final static Singleton2 uniqueInstance = new Singleton2();
    private Singleton2(){}
    public static Singleton2 getInstance(){
        return uniqueInstance;
    }
}

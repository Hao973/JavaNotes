package hao.designpattern.singleton;

public class Singleton1 {
    private static Singleton1 uniqueInstance;
    private Singleton1(){}
    public static Singleton1 getInstance(){
        if(null == uniqueInstance){
            uniqueInstance = new Singleton1();
        }
        return uniqueInstance;
    }
}

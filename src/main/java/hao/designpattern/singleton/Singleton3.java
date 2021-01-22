package hao.designpattern.singleton;

public class Singleton3 {
    private static Singleton3 uniqueInstance;
    private Singleton3(){

    }
    public static Singleton3 getInstance(){
        if(null == uniqueInstance){
            synchronized(Singleton3.class){
                if(null == uniqueInstance){
                    uniqueInstance = new Singleton3();
                }
            }
        }
        return uniqueInstance;
    }
}

package hao.designpattern.factory;

public class Client {
    public static void main(String[] args) {
        Factory ft = new ConcreteFactory();
        ft.doSomething();
        ft = new ConcreteFactory1();
        ft.doSomething();
    }
}

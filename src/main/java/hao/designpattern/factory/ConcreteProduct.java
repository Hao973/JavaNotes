package hao.designpattern.factory;

public class ConcreteProduct implements Product{
    @Override
    public void product() {
        System.out.println("ConcreteProduct");
    }
}

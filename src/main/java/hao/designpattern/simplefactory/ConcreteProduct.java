package hao.designpattern.simplefactory;

public class ConcreteProduct implements Product{
    @Override
    public void product() {
        System.out.println("ConcreteProduct");
    }
}

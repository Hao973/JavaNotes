package hao.designpattern.simplefactory;

public class SimpleFactory {
    public Product createProduct(int type){
        if(1 == type){
            return new ConcreteProduct1();
        }else if(2 == type){
            return new ConcreteProduct2();
        }
        return new ConcreteProduct();
    }
}

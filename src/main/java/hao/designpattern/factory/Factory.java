package hao.designpattern.factory;

public abstract  class Factory {
    abstract public Product factoryMethod();
    public void doSomething(){
        Product pt = this.factoryMethod();
        pt.product();
    }
}

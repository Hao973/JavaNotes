package hao.designpattern.simplefactory;

public class Client {
    public static void main(String[] args) {
        SimpleFactory sf = new SimpleFactory();
        Product pt = sf.createProduct(1);
        pt.product();
        pt = sf.createProduct(0);
        pt.product();
    }
}

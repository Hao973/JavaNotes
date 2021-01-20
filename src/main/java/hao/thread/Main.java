package hao.thread;

public class Main {
    public static void main(String[] args) {
        // Thread t = new Thread();
//        Thread t = new ThreadCreateDemo();
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}

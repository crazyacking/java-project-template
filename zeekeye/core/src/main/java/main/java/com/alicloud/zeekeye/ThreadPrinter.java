package main.java.com.alicloud.zeekeye;

/**
 * @author Mengyu
 * @date 2016/10/19
 */
public class ThreadPrinter implements Runnable {
    private String name;
    private Object prev;
    private Object self;

    private ThreadPrinter(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ThreadPrinter aPrinter = new ThreadPrinter("A", c, a);
        ThreadPrinter bPrinter = new ThreadPrinter("B", a, b);
        ThreadPrinter cPrinter = new ThreadPrinter("C", b, c);

        new Thread(aPrinter).start();
        new Thread(bPrinter).start();
        new Thread(cPrinter).start();
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;

                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

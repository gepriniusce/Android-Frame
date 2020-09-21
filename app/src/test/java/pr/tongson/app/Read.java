package pr.tongson.app;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/9/21
 * @Version
 * @Since
 * @Description
 */
class Read {

    private Object mLock = new Object();

    public Read() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mLock) {
                    try {
                        mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Lock.getInstance().setA(4);
                    Lock.getInstance().setB(3);
                    Lock.getInstance().setC(2);
                    Lock.getInstance().setD(1);
                    mLock.notify();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mLock) {
                    try {
                        mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Lock.getInstance().setA(5);
                    Lock.getInstance().setB(6);
                    Lock.getInstance().setC(7);
                    Lock.getInstance().setD(8);
                    mLock.notify();
                }

            }
        }).start();
        for (int i = 0; i < 100; i++) {
            synchronized (mLock) {
                 mLock.notify();
                System.out.println("---");
                System.out.print(Lock.getInstance().getA());
                System.out.print(Lock.getInstance().getB());
                System.out.print(Lock.getInstance().getC());
                System.out.println(Lock.getInstance().getD());
                System.out.println("---");
            }
        }
    }
}

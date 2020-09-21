package pr.tongson.app;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/9/21
 * @Version
 * @Since
 * @Description
 */
class Lock {
    private static final Lock ourInstance = new Lock();

    private Object llll=new Object();

    static Lock getInstance() {
        return ourInstance;
    }

    private Lock() {
    }

    int a = 1;
    int b = 2;
    int c = 3;
    int d = 4;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    //    public int getA() {
//        synchronized (llll) {
//            return a;
//        }
//    }
//
//    public void setA(int a) {
//        synchronized (llll) {
//            this.a = a;
//        }
//    }
//
//    public int getB() {
//        synchronized (llll) {
//            return b;
//        }
//    }
//
//    public void setB(int b) {
//        synchronized (llll) {
//            this.b = b;
//        }
//    }
//
//    public int getC() {
//        synchronized (llll) {
//            return c;
//        }
//    }
//
//    public void setC(int c) {
//        synchronized (llll) {
//            this.c = c;
//        }
//    }
//
//    public int getD() {
//        synchronized (llll) {
//            return d;
//        }
//    }
//
//    public void setD(int d) {
//        synchronized (llll) {
//            this.d = d;
//        }
//    }
}

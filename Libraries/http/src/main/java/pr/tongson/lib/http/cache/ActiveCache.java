package pr.tongson.lib.http.cache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/24
 * @Version
 * @Since
 * @Description
 */
public class ActiveCache<T> {


    private boolean isCloseThread = false;

    private Map<String, WeakReference<T>> mMap = new HashMap<>();
    /**
     * 目的：监听弱引用是否被回收了
     */
    private ReferenceQueue<T> mQueue;

    private Thread mRemoveThread;

    /**
     * 手动移除
     */
    private boolean isInitiativeRemove = false;

    /**
     * 添加活动缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, T value) {
        mMap.put(key, new CustomWeakReference(value, getQueue(), key));
    }

    /**
     * @param key
     * @return
     */
    public T get(String key) {
        WeakReference<T> valueWeakReference = mMap.get(key);
        if (null != valueWeakReference) {
            return valueWeakReference.get();
        }
        return null;
    }

    /**
     * 手动移除
     *
     * @param key
     * @return
     */
    public T remove(String key) {
        isInitiativeRemove = true;
        WeakReference<T> valueWeakReference = mMap.remove(key);
        isInitiativeRemove = false;
        if (null != valueWeakReference) {
            return valueWeakReference.get();
        }
        return null;
    }

    /**
     * 释放，关闭线程
     */
    public void closeThread() {
        isCloseThread = true;
        if (null != mRemoveThread) {
            // 中断线程
            mRemoveThread.interrupt();
            // 线程稳定安全地停止下来
            try {
                mRemoveThread.join(TimeUnit.SECONDS.toMillis(5));
                if (mRemoveThread.isAlive()) {
                    throw new IllegalStateException("活动缓存中关闭线程,线程没有停止下来...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 监听弱引用，成为弱引用的子类，监听弱引用是否被回收了
     */
    public class CustomWeakReference extends WeakReference<T> {

        private String mKey;

        public CustomWeakReference(T referent, ReferenceQueue<? super T> q, String key) {
            super(referent, q);
            this.mKey = key;
        }
    }

    private ReferenceQueue<T> getQueue() {
        if (mQueue == null) {
            mQueue = new ReferenceQueue<>();
            mRemoveThread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        while (!isCloseThread) {
                            // mQueue.remove();阻塞
                            // 如果已经被回收了就会执行remove()
                            Reference<? extends T> removed = mQueue.remove();
                            CustomWeakReference removedWeakReference = (CustomWeakReference) removed;
                            if (mMap != null && !mMap.isEmpty() && !isInitiativeRemove) {
                                mMap.remove(removedWeakReference.mKey);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            mRemoveThread.start();
        }
        return mQueue;
    }


}

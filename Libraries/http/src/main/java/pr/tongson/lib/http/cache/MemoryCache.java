package pr.tongson.lib.http.cache;

import android.util.LruCache;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/24
 * @Version
 * @Since
 * @Description LruCache
 * 1.重复的key会被移除。
 * 2.最少使用的key会被移除。
 */
public class MemoryCache<T> extends LruCache<String, T> {

    private RemovedListener mRemovedListener;
    /**
     * 手动移除
     */
    private boolean isInitiativeRemove = false;

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public MemoryCache(int maxSize) {
        super(maxSize);
    }


    public void initiativeRemove(String key) {
        remove(key);
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, T oldValue, T newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if (mRemovedListener != null && !isInitiativeRemove) {
            mRemovedListener.onMemoryCacheEntryRemoved(key, oldValue);
        }
    }

    public void setRemovedListener(RemovedListener<T> removedListener) {
        mRemovedListener = removedListener;
    }

    public interface RemovedListener<T> {
        void onMemoryCacheEntryRemoved(String key, T oldValue);
    }
}

package model;
import java.util.*;

public class SimpleCacheManager {

    private static SimpleCacheManager instance;
    private static Object monitor = new Object();
    private Map<Integer, String> cache = Collections.synchronizedMap(new HashMap<Integer, String>());

    public SimpleCacheManager() {
    }

    public void put(Integer cacheKey, String value) {
        cache.put(cacheKey, value);
    }

    public Object get(Integer cacheKey) {
        return cache.get(cacheKey);
    }

    public void clear(Integer cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public static SimpleCacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new SimpleCacheManager();
                }
            }
        }
        return instance;
    }

}
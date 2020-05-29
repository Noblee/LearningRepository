package interview;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUcache<K, V> extends LinkedHashMap<K, V> {
    final private int capacity;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() >= capacity;
    }

    LRUcache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public synchronized V get(Object key) {
        return super.get(key);
    }

    @Override
    public synchronized V put(K key, V value) {
        return super.put(key, value);
    }

    public static void main(String[] args) {
        LRUcache<Object, Object> lrUcache = new LRUcache<>(2);
        lrUcache.put("1", 1);
        lrUcache.put("2", 1);
        lrUcache.get("1");
        lrUcache.put("3", 1);
        System.out.println(lrUcache.get("2"));
    }
}

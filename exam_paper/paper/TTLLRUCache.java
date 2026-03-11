package paper;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目8：带 TTL 的 LRU Cache
 *
 * 支持 Get / Put 操作，Get 和 Put 平均 O(1)
 * 过期 key 不可被 Get，空间不足时优先淘汰过期 key，其次淘汰 LRU key
 *
 * follow-up：并发安全版本 → 分段锁 sharding
 */
public class TTLLRUCache {

    // TODO: 实现双向链表节点
//    static class Node {
//        int key, value;
//        long expireAt; // 过期时间戳（毫秒）
//        Node prev, next;
//
//        Node(int key, int value, long expireAt) {
//            this.key = key;
//            this.value = value;
//            this.expireAt = expireAt;
//        }
//    }
//
//    private final int capacity;
//    private final Map<Integer, Node> map;
//    private final Node head, tail; // 哨兵节点
//
//    public TTLLRUCache(int capacity) {
//        this.capacity = capacity;
//        this.map = new HashMap<>();
//        this.head = new Node(-1, -1, Long.MAX_VALUE);
//        this.tail = new Node(-1, -1, Long.MAX_VALUE);
//        head.next = tail;
//        tail.prev = head;
//    }
//
//    public int get(int key) {
//        if (!map.containsKey(key)) return -1;
//        Node node = map.get(key);
//        if (System.currentTimeMillis() > node.expireAt) {
//            removeNode(node);
//            map.remove(key);
//            return -1;
//        }
//        moveToHead(node);
//        return node.value;
//    }
//
//    public void put(int key, int value, int ttlSeconds) {
//        long expireAt = System.currentTimeMillis() + ttlSeconds * 1000L;
//        if (map.containsKey(key)) {
//            Node node = map.get(key);
//            node.value = value;
//            node.expireAt = expireAt;
//            moveToHead(node);
//            return;
//        }
//        if (map.size() >= capacity) {
//            evict();
//        }
//        Node node = new Node(key, value, expireAt);
//        map.put(key, node);
//        addToHead(node);
//    }
//
//    private void evict() {
//        // 优先淘汰已过期的 key（从尾部开始找）
//        long now = System.currentTimeMillis();
//        Node cur = tail.prev;
//        while (cur != head) {
//            if (now > cur.expireAt) {
//                Node toRemove = cur;
//                cur = cur.prev;
//                removeNode(toRemove);
//                map.remove(toRemove.key);
//                return;
//            }
//            cur = cur.prev;
//        }
//        // 没有过期的 key，淘汰 LRU（尾部）
//        Node lru = tail.prev;
//        if (lru != head) {
//            removeNode(lru);
//            map.remove(lru.key);
//        }
//    }
//
//    private void addToHead(Node node) {
//        node.next = head.next;
//        node.prev = head;
//        head.next.prev = node;
//        head.next = node;
//    }
//
//    private void removeNode(Node node) {
//        node.prev.next = node.next;
//        node.next.prev = node.prev;
//    }
//
//    private void moveToHead(Node node) {
//        removeNode(node);
//        addToHead(node);
//    }

    public static void main(String[] args) {
//        TTLLRUCache cache = new TTLLRUCache(2);
//        cache.put(1, 100, 2); // TTL = 2秒
//        cache.put(2, 200, 5); // TTL = 5秒
//        System.out.println(cache.get(1)); // 100
//        try { Thread.sleep(3000); } catch (InterruptedException e) {}
//        System.out.println(cache.get(1)); // -1 (已过期)
//        System.out.println(cache.get(2)); // 200 (未过期)
//        cache.put(3, 300, 5);
//        System.out.println(cache.get(2)); // 200
//        System.out.println(cache.get(3)); // 300
    }
}

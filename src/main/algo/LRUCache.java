import java.util.HashMap;
import java.util.Map;

public class LRUCache<T, K> {

    private int capacity = 0;

    private Node<T, K> head = new Node<>();

    private Node<T, K> tail = new Node<>();

    public LRUCache(int capacity) {
        head.setNext(tail);
        head.setFront(tail);
        tail.setNext(head);
        tail.setFront(head);
        this.capacity = capacity;
    }

    Map<T, Node<T, K>> cache = new HashMap<>(capacity);

    public K get(T key) {
        if(cache.containsKey(key)){
            Node<T, K> kNode = cache.get(key);
            Node<T, K> next = kNode.getNext();
            Node<T, K> front = kNode.getFront();
            front.setNext(next);
            next.setFront(front);
            Node<T, K> tailFront = tail.getFront();
            tailFront.setNext(kNode);
            kNode.setNext(tail);
            kNode.setFront(tail.getFront());
            tail.setFront(kNode);
        }
        return cache.get(key).getValue();
    }

    public void put(T key, K value) {

        if (!cache.containsKey(key)) {
            if (cache.size() == this.capacity) {
                Node next = head.getNext();
                cache.remove(next.getKey());
                Node<T, K> nextNext = head.getNext().getNext();
                nextNext.setFront(head);
                head.setNext(nextNext);
            }
            Node<T, K> node = new Node<>(key, value);
            cache.put(key, node);
            Node<T, K> front = tail.getFront();
            front.setNext(node);
            node.setNext(tail);
            node.setFront(front);
            tail.setFront(node);
        } else {
            Node<T, K> kNode = cache.get(key);
            Node<T, K> next = kNode.getNext();
            Node<T, K> front = kNode.getFront();
            front.setNext(next);
            next.setFront(front);
            kNode.setNext(tail);
            kNode.setFront(tail.getFront());
            kNode.setValue(value);
            tail.setFront(kNode);
        }
    }

    public void out() {
        Node<T,K> n = head;
        while (n != tail.getFront()){
            n = n.getNext();
            System.out.println((n.getKey() + ":" + n.getValue()));
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(3);
        lruCache.put(1, 2);
        lruCache.put(2, 2);
        lruCache.put(3, 2);
        lruCache.get(2);
        lruCache.put(4, 2);
        lruCache.out();

    }
}

class Node<T, K> {
    private T key;
    private K value;
    private Node<T, K> next;
    private Node<T, K> front;

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    public Node(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public Node() {
    }

    public Node(K value) {
        this.value = value;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T, K> getNext() {
        return next;
    }

    public void setNext(Node<T, K> next) {
        this.next = next;
    }

    public Node<T, K> getFront() {
        return front;
    }

    public void setFront(Node<T, K> front) {
        this.front = front;
    }
}



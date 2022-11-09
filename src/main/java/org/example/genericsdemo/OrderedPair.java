package org.example.genericsdemo;

public class OrderedPair <K,V> implements PairInterface<K, V> {
    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getPairKey() {
        return key;
    }

    @Override
    public V getPairValue() {
        return value;
    }

    public static void main(String[] args) {
        OrderedPair<String, Integer> pair1 = new OrderedPair<>("Even" , 8);
        System.out.println(pair1.getPairKey());
        System.out.println(pair1.getPairValue());
    }
}

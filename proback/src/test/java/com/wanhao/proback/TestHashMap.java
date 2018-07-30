package com.wanhao.proback;

/**
 * Created by LiuLiHao on 2018/7/30 21:17.
 * 描述：
 * 作者： LiuLiHao
 */
public class TestHashMap {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        hashMap.put("2","1");
        hashMap.put("3","1");
        hashMap.put("4","1");
        hashMap.put("5","1");
        hashMap.put("6","1");
        hashMap.put("7","1");
        hashMap.put("8","1");
        hashMap.put("9","1");
        hashMap.put("0","1");
        hashMap.put("11","1");
        hashMap.put("12","1");
        System.out.println(hashMap.get("1"));

    }

}
interface Map<K,V>{

    V put(K k,V v);

    V get(K k);

    int size();

    interface Entry<K,V>{

        K getKey();

        V getValue(K k);

        V setValue(V v);
    }
}

class HashMap<K,V> implements Map<K,V>{

    Node[] array;
    private static int capacity  = 1<<4; //16

    private static float loadFactor = 0.75f;

    private int size = 0;

    public HashMap() {
        this.array = new Node[capacity];
    }

    @Override
    public V put(K k, V v) {
        int position = getPosition(k,capacity);
        //是否需要扩容
        if (size> capacity * loadFactor){
            resize();
        }
        Node<K,V> oldNode = array[position];

        if (oldNode==null){
            //没有值 直接放入
            array[position] = new Node<K,V>(k,v,null);
            size++;
        }else {
            //有值了
            if (oldNode.key.equals(k) || oldNode.key==k){
                //key相同直接替换内容
                return oldNode.setValue(v);
            }else {
                array[position] = new Node<K,V>(k,v,oldNode);
                size++;
            }
        }
        return null;
    }

    /**
     * 扩容
     */
    private void resize() {
        Node<K,V>[] oldArray = this.array;
        Node<K,V>[] newArray = new Node[capacity<<1];

        for(int i=0;i<oldArray.length;i++){
            Node<K, V> oldNode = oldArray[i];
            while (oldNode!=null){
                int position = getPosition(oldNode.key,newArray.length);
                Node<K, V> next = oldNode.next;
                oldNode.next = newArray[position];
                newArray[position] = oldNode;
                oldNode = next;
            }
        }
        array = newArray;
        capacity = newArray.length;
        newArray = null;
    }


    private int getPosition(K k,int length) {
        return k.hashCode() % (length-1);
    }

    @Override
    public V get(K k) {
        //计算位置
        int position = getPosition(k,capacity);

        Node<K,V> node = array[position];
        while (node!=null){
            if (node.key==k || node.key.equals(k)){
                return node.value;
            }else {
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    class Node<K,V> implements Entry<K,V>{
        K  key;
        V value;
        Node<K,V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue(K k) {
            return value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = value;
            value = v;

            return oldValue;
        }
    }
}
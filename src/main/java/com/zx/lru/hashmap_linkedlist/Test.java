package com.zx.lru.hashmap_linkedlist;

/**
 * 测试基于hashmap和双向链表实现的LRU缓存
 * @author xuanzhou
 * @date 2020/12/29 4:38 下午
 */
public class Test {

    public static void main(String[] args) {
        LRUCahce cache = new LRUCahce(5);

        cache.put("1","1");
        cache.put("2","2");
        cache.put("3","3");
        cache.put("4","4");
        cache.put("5","5");
        print(cache.getHead());
        System.out.println(cache.get("1"));
        print(cache.getHead());

        cache.put("6","6");
        print(cache.getHead());
    }


    public static void print(Node head){
        Node node = head;
        do{
            System.out.println(node.getKey());
            node = node.getNext();
        }while (node !=  null);
    }
}

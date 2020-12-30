package com.zx.lru.linkedhashmap;

import java.util.Set;

/**
 * 测试基于LinkedHashMap实现LRU算法
 * @author xuanzhou
 * @date 2020/12/30 10:40 上午
 */
public class Test {

    public static void main(String[] args) {
        LRUCache<String,String> cache = new LRUCache(5);

        cache.put("1","1");
        cache.put("2","2");
        cache.put("3","3");
        cache.put("4","4");
        cache.put("5","5");
        print(cache);
        System.out.println(cache.get("1"));
        print(cache);
        cache.put("6","6");
        print(cache);
    }


    public static void print(LRUCache cache){
        Set<String> set = cache.keySet();
        for (String s:set
             ) {
            System.out.println(s);
        }
    }
}

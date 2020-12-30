package com.zx.lru.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用LinkedHashMap实现LRU算法
 * @author xuanzhou
 * @date 2020/12/30 10:32 上午
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private int max_size;

    public LRUCache(int size){
        super((int) Math.ceil(size / 0.75) + 1, 0.75f, true);
        this.max_size = size;
    }
    /**
     * LinkedHashMap：继承HashMap,HashMap是无序集合，LinkedHashMap是有序的，默认顺序是插入顺序，accessOrder = false
     * accessOrder = true ：代表按照访问顺序进行排序。
     */

    /**
     * 重写该方法是因为：LinkedHashMap在put()方法之后，需要判断是否进行删除原来的元素，默认为false
     * LRU需要在put之后判断是否大于最大容量，若大于，就要删除原节点。
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>max_size;
    }
}

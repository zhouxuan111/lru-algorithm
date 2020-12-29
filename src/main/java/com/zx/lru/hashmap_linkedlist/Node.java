package com.zx.lru.hashmap_linkedlist;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 链表节点
 * @author xuanzhou
 * @date 2020/12/29 2:46 下午
 */
@Getter
@Setter
public class Node {

    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 前驱节点
     */
    private Node pre;

    /**
     * 后继节点
     */
    private Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

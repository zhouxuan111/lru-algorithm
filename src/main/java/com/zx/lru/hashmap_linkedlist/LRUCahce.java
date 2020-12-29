package com.zx.lru.hashmap_linkedlist;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 基于hashmap和双向链表实现的LRU缓存
 * @author xuanzhou
 * @date 2020/12/29 2:54 下午
 */
@Getter
@Setter
public class LRUCahce {

    /**
     * 设计思路：使用Map存放缓存，缓存数据使用双向链表连接
     * 链表头部代表未被访问数据，链表尾部存放最近被访问的数据
     *
     * 删除操作：删除链表中的节点，删除集合中的缓存数据
     * 获取操作：根据key查询数据，
     *          数据不存在，直接返回null,
     *          数据存在，进行刷新操作 -> （删除原数据的节点位置，将节点插入链表尾部，变更为最近访问数据）。
     *
     * 添加操作：根据key查询数据
     *          数据不存在，判断缓存的数据是否超过最大阈值，超过，将头部数据淘汰删除；将新生成节点插入链表尾部
     *          数据存在，刷新操作 -> (删除原数据的节点位置，将节点插入到链表尾部，代表最近访问数据)
     */

    /**
     * 链表头：存放最久未被访问数据
     */
    private Node head;

    /**
     * 链表尾：存放最近被访问数据
     */
    private Node tail;

    private Integer size;

    private Map<String,Node> data;

    public LRUCahce(Integer size) {
        this.size = size;
        data = new HashMap<>();
    }

    /**
     * 删除节点：单独判断 头和尾
     * @param node
     */
    public void removeNode(Node node) {
        if (tail == node) {
            tail = tail.getPre();
        }else if (head == node) {
            head = head.getNext();
        }else {
            node.getPre().setNext(node.getNext());
            node.getNext().setPre(node.getPre());
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public void  remove(String key){
        Node node = data.get(key);
        if(null != node){
            removeNode(node);
            data.remove(key);
        }
    }

    public void refreshNode(Node node){
        //如果访问的是尾节点，则不需要操作
        if(tail == node){
            return;
        }
        //删除原节点位置
        removeNode(node);
        //将节点插入链表尾部
        addNodeToTail(node);
    }

    /**
     * 将节点插入链表尾部
     * @param node
     */
    public void addNodeToTail(Node node){
        if(tail != null){
            tail.setNext(node);
            node.setNext(null);
            node.setPre(tail);
        }
        tail = node;
        if(null == head){
            head = node;
        }
    }

    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    public String get(String key){
        Node node = data.get(key);
        //数据不存在，直接返回null
        if(null == node){
            return null;
        }
        //数据存在，进行刷新操作（删除原位置的节点，将节点插入链表尾部）
        refreshNode(node);
        return node.getValue();
    }

    /**
     * 存放缓存
     * @param key
     * @param value
     */
    public void put(String key,String value){
        Node node = data.get(key);
        //缓存不存在，判断缓存容量是否到达最大值，到达最大值，删除链表头部数据，然后将节点插入链表尾部
        if(null == node){
            if(data.size()>=size){
                //删除链表头部的缓存数据
                data.remove(head.getKey());
                removeNode(head);
            }
            //将数据插入
            node = new Node(key,value);
            addNodeToTail(node);
            data.put(key,node);
        }
        //数据存在，进行刷新操作
        else {
            node.setValue(value);
            refreshNode(node);
        }
    }
}

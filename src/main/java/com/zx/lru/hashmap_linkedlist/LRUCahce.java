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
     * 最久未被访问元素
     */
    private Node head;

    /**
     * 最近被访问元素
     */
    private Node tail;

    private Integer size;

    private Map<String,Node> map;

    public LRUCahce(Integer size) {
        this.size = size;
        map = new HashMap<>();
    }

    /**
     * 根据key查询节点的值：
     *  不存在：直接返回null
     *  存在，删除原数据，将value插入到双向链表的头
     * @param key
     * @return
     */
    public String get(String key){
        Node node = map.get(key);
        //不存在，直接返回null
        if(null == node){
            return null;
        }
        //存在，删除原数据的节点
        removeNode(node);
        //将数据插入到链表尾部
        addNodeToTail(node);
        return node.getValue();
    }

    /**
     * 删除缓存key，既要从缓存中删除，也要从map中删除
     * @param key
     * @return
     */
    public String remove(String key){
        Node node  = map.get(key);
        //既要从缓存中删除，也要从集合中删除
        removeNode(node);
        map.remove(key);
        return node.getKey();
    }

    /**
     * 添加缓存
     * @param key
     * @param value
     * @return
     */
    public Node put(String key,String value){
        Node node = map.get(key);
        //不存在,判断是否达到内存容量，若达到进行删除（map+链表）
        if(node == null){
            //判断是否缓存数量最大值,若查过，删除尾结点数据
            if(map.size()>= size){
                removeNode(head);
                map.remove(head.getKey());
            }
            node  = new Node(key,value);
            //将数据添加到链表尾部
            addNodeToTail(node);
            map.put(key,node);
        }
        //存在，删除原节点，变更value值，将新的Node 插入尾部
        else {
            removeNode(node);
            node.setValue(value);
            addNodeToTail(node);
        }
        return node;
    }

    /**
     * 删除节点
     * @param node
     */
    public void removeNode(Node node){
        //正好是尾结点 移除尾结点
        if(node == tail){
            tail = tail.getPre();
        }
        //正好是头结点
        if(node == head){
            head = head.getNext();
        }
        //移除中间节点
        node.getPre().setNext(node.getNext());
        node.getNext().setPre(node.getPre());

    }

    /**
     * 在尾部插入节点
     * @param node
     */
    public void addNodeToTail(Node node){
        if(tail != null){
            tail.setNext(node);
            node.setNext(null);
            node.setPre(tail);
        }
        tail = node;
        if(head == null){
            head = node;
        }
    }

}

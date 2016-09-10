package com.zizhu.structure.tree.bplus;

import java.util.Random;

/**
 * 
 * <pre>
B+树，mysql索引实现原理:

1.任意非叶子结点最多有M个子节点；且M>2；

2.除根结点以外的非叶子结点至少有 M/2个子节点；

3.根结点至少有2个子节点；

4.除根节点外每个结点存放至少M/2和至多M个关键字；（至少2个关键字）

5.非叶子结点的子树指针与关键字个数相同；

6.所有结点的关键字：K[1], K[2], …, K[M]；且K[i] < K[i+1]；

7.非叶子结点的子树指针P[i]，指向关键字值属于[K[i], K[i+1])的子树；

8.所有叶子结点位于同一层；

5.为所有叶子结点增加一个链指针；

6.所有关键字都在叶子结点出现；

总结如下：
	root节点的子节点数：2<=n<M
	普通非叶子节点个数：    M/2<n<=M(且n>=2)

参考示例(M=3)：<br/>
<img src="http://p.blog.csdn.net/images/p_blog_csdn_net/manesking/5.JPG" />
 * </pre>
 *
 * 
 * @author Administrator
 * @see http://www.blogjava.net/supercrsky/articles/185167.html
 */
public class BPlusTree implements IBPlus {
    
    /** 根节点 */  
    protected Node root;
      
    /** 阶数，M值 */  
    protected int order;
      
    /** 叶子节点的链表头*/  
    protected Node head;
      
    public Node getHead() {
        return head;
    }  
  
    public void setHead(Node head) {
        this.head = head;
    }  
  
    public Node getRoot() {
        return root;
    }  
  
    public void setRoot(Node root) {
        this.root = root;
    }  
  
    public int getOrder() {
        return order;
    }  
  
    public void setOrder(int order) {
        this.order = order;
    }  
  
    @Override  
    public Object get(Comparable key) {
        return root.get(key);
    }  
  
    @Override  
    public void remove(Comparable key) {
        root.remove(key, this);
  
    }  
  
    @Override  
    public void insertOrUpdate(Comparable key, Object obj) {
        root.insertOrUpdate(key, obj, this);
  
    }  
      
    public BPlusTree(int order){
        if (order < 3) {
            System.out.print("order must be greater than 2");
            System.exit(0);
        }  
        this.order = order;
        root = new Node(true, true);
        head = root;
    }  
      
    //测试  
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree(6);
        Random random = new Random();
        long current = System.currentTimeMillis();
        for (int j = 0; j < 1000; j++) {
            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000);
                tree.insertOrUpdate(randomNumber, randomNumber);
            }  
  
            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000);
                tree.remove(randomNumber);
            }  
        }  
  
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
        int search = 80;
        System.out.print(tree.get(search));
    }  
  
} 

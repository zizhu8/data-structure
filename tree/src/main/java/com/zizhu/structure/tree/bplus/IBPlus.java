package com.zizhu.structure.tree.bplus;


/**
 * @author Administrator
 * 
 * @see http://kevindude.iteye.com/blog/846726
 *
 */
public interface IBPlus {  
       public Object get(Comparable key); //查询  
       public void remove(Comparable key);  //移除  
       public void insertOrUpdate(Comparable key, Object obj); //插入或者更新，如果已经存在，就更新，否则插入  
}  
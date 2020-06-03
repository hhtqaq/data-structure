##平衡二叉树
### 1.1 介绍
&emsp;&emsp;二叉排序树查找算法的性能取决于二叉树的结构，而二叉排序树的形状则取决于其数据集。
如果数据呈有序排列，则二叉排序树是线性的，查找的时间复杂度为O(n);反之，如果二叉排序树的结构
合理，则查找速度较快，查找的时间复杂度为O(logn)。事实上，树的高度越小，查找的速度越快。因此
，希望二叉树的高度尽可能小。本节将讨论一种特殊的二叉排序树，称为为平衡二叉树（Balanced Binary 
Tree），因由前苏联数学家Adelson-Velskii和Landis提出，所以又称为AVL树。

#### 1.1.1 二叉排序树的定义
&emsp;&emsp;二叉排序树是一颗空树，或者是具有下列性质的二叉排序树。

1）左子树和右子树的深度之差绝对值不超过1。

2）左子树和右子树也是平衡二叉树。

&emsp;&emsp;若将二叉树上节点的**平衡因子**定义为该节点的左右子树的深度之差，则平衡二叉树上所有
结点的平衡因子只可能是-1、0和1。所以只要二叉树上有一个结点的平衡因子的绝对值大于1，则该二叉
树就是不平衡的。

&emsp;&emsp;因为AVL树上人和结点的左右子树的深度之差都不差过1，则可以证明他的时间复杂度为logn。

#### 1.1.2 平衡二叉树的平衡调整方法
&emsp;&emsp;如何创建一颗平衡二叉树呢？插入结点时，首先按照二叉排序树处理，若插入结点后破坏了
平衡二叉树的特性，需对平衡二叉树进行调整。调整方法是：找到里插入节点最近且平衡因子绝对值超过
1的祖先结点，以该节点为根的字数称为最小不平衡子树，可将重新平衡的范围局限于这棵子树。

&emsp;&emsp;一般情况下，假设最小不平衡子树的根结点为A，则失去平衡后进行调整的规律课归纳为下列4
种情况。

 - (1)LL型(插入发生在左子树的左结点)：右旋
 
  ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myAvlTree/img-file/ll.png)
 - (2)RR型(插入发生在右子树的右结点)：左旋
 
  ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myAvlTree/img-file/rr.png)
 - (3)LR型(插入发生在左子树的右结点)：先左旋变成LL型，再右旋
 
  ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myAvlTree/img-file/lr.png)
 - (4)RL型(插入发生在右子树的左结点)：先右旋变成RR型，再左旋
 
  ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myAvlTree/img-file/rl.png)
  
#### 1.1.3 平衡二叉树的插入

   - 【算法思想】
   
     1.二叉排序树的插入
    
     2.插入后高度修改为左右最大子树高度+1
     
     3.判断左右子树高度差是否小于2，判断结点是否失衡
     
     4.当前结点node失衡：
     
     插入发生在左子树的左结点，单右旋singleRotateRight(node)
      
     插入发生在左子树的右结点，双右旋doubleRotateRight(node)
    
     插入发生在右子树的右结点，右旋singleRotateLeft(node)
    
     插入发生在右子树的左结点，右旋doubleRotateLeft(node)

    
**说明：**

&emsp;&emsp;完全二叉树，二叉查找树，平衡二叉树都是从**内存**中查找数据，相对顺序存储、链表来说
能够提升查找效率，但如果是海量数据，无法一次性读到内存中时，这些数据结构就不在适用。
此时就需要研究从**磁盘**中查找海量数据的方法，就引入了b树和b+树
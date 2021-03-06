## 红黑树
### 1 介绍
&emsp;&emsp;为了减少平衡二叉树为了保证高度平衡花费的动态插入和删除的代价，产生的自
平衡红黑树，是一种高效的查找树。它是由 Rudolf Bayer于1978年发明，在当时被称为对称
二叉 B 树(symmetric binary B-trees)。后来，在1978年被Leo J. Guibas 和 Robert Sedgewick 
修改为如今的红黑树。红黑树具有良好的效率，它可在O(logN) 时间内完成查找、增加、删除等操作。
因此，红黑树在业界应用很广泛，比如 Java 中的 TreeMap，JDK 1.8 中的 HashMap、C++ STL 中的
 map 均是基于红黑树结构实现的。考虑到红黑树是一种被广泛应用的数据结构，所以我们很有必
 要去弄懂它。

#### 1.1 红黑树的定义
&emsp;&emsp;红黑树通过如下的性质定义实现自平衡：

 - 1）结点是红色或者黑色。

 - 2）根是黑色。
 
 - 3）所有叶子结点都是黑色（叶子是NIL结点）
 
 - 4）每个红色结点必须有两个黑色的子结点（从每个叶子到根的所有路径上不能有两个连续的红色结点）
 
 - 5）从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点（简称黑高）
 
&emsp;&emsp;有了上面的几个性质作为限制，即可避免二叉查找树退化成单链表的情况。但是，
仅仅避免这种情况还不够，这里还要考虑某个节点到其每个叶子节点路径长度的问题。如果某些
路径长度过长，那么，在对这些路径上的结点进行增删查操作时，效率也会大大降低。
这个时候性质4和性质5用途就凸显了，有了这两个性质作为约束，即可保证任意节点到其每个叶
子节点路径最长不会超过最短路径的2倍。原因如下：

&emsp;&emsp;当某条路径最短时，这条路径必然都是由黑色节点构成。当某条路径长度最长时，这条路径
必然是由红色和黑色节点相间构成（性质4限定了不能出现两个连续的红色节点）。而性质5又限定了
从任一节点到其每个叶子节点的所有路径必须包含相同数量的黑色节点。此时，在路径最长的情况下，
路径上红色节点数量 = 黑色节点数量。该路径长度为两倍黑色节点数量，也就是最短路径长度的2倍。

#### 1.2 RBT的插入修复算法
&emsp;&emsp;同二叉查找树的插入算法：
 - 1 找到待插入结点的位置
 - 2 插入该节点
 - 3 将该节点着色为红色
 - 4 最后为了保证红黑树的性质在插入之后任然保持，对节点进行修复。<br/>
    如果插入的是根节点，由于原树是空树，因此会违反**根是黑色的性质**，因此将该节点改为黑色。如果插入
    的结点的父结点是黑色，由于此不会违反性质2和性质4，红黑树没有被破坏，所以此时什么也不做。
  
    但当遇到下述3种情况时又该如何调整呢？

    ● 插入修复情况1：如果当前结点的父结点是红色且祖父结点的另一个子结点（叔叔结点）是红色

    ● 插入修复情况2：当前节点的父节点是红色,叔叔节点是黑色，当前节点是其父节点的右孩子

    ● 插入修复情况3：当前节点的父节点是红色,叔叔节点是黑色，当前节点是其父节点的左孩子

     - (1) 插入修复情况1：当前结点的父结点是红色，祖父结点的另一个子结点（叔叔结点）是红色。
          
          此时父结点的父结点一定存在，否则插入前就已不是红黑树。与此同时，又分为父结点是祖父结点的左孩子
          还是右孩子，根据对称性，我们只要解开一个方向就可以了。这里只考虑父结点为祖父左孩子的情况，
          如下图所示（勘误：图中15节点应改为13，特此说明，下同）：
          
      ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myRBTree/img-file/1.png)
     
     对此，我们的解决策略是：将当前节点的父节点和叔叔节点涂黑，祖父结点涂红，把当前结点指向祖父节点，
     从新的当前节点重新开始算法。即如下代码所示：
     
            if (isRed(uncle)) {
                                setBlack(uncle);
                                setBlack(parent);
                                setRed(gparent);
                                node = gparent;
                                continue;
             }
        
      变化后如下图所示：
        
      ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myRBTree/img-file/1.1.png)
        
      于是，插入修复情况1转换成了插入修复情况2。
        
      - (2)  当前节点的父节点是红色,叔叔节点是黑色，当前节点是其父节点的右孩子 <br/>
      此时，解决对策是：当前节点的父节点做为新的当前节点，以新当前节点为支点左旋。
      即如下代码所示：
      
            if (parent.right == node) {
                                RBTNode<T> tmp;
                                leftRotate(parent);
                                tmp = parent;
                                parent = node;
                                node = tmp;
                            }
                            
      红黑树由之前
       
      ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myRBTree/img-file/1.1.png)

      变化成：
      
      ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myRBTree/img-file/2.png)

      从而插入修复情况2转换成了插入修复情况3。
    
      - (3) 当前节点的父节点是红色,叔叔节点是黑色，当前节点是其父节点的左孩子<br/>
      解决对策是：父节点变为黑色，祖父节点变为红色，在祖父节点为支点右旋，操作代码为：
      
            setBlack(parent);
            setRed(gparent);
            rightRotate(gparent);
      
      最后，把根结点涂为黑色，整棵红黑树便重新恢复了平衡。所以红黑树由之前的：
      
     ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myRBTree/img-file/2.png)
     
     变化成：
     
     ![Image text](https://github.com/hhtqaq/data-structure/raw/master/myRBTree/img-file/3.png)

     

#### 1.3 RBT 的操作代价分析：

 - (1) 查找代价：
由于红黑树的性质(最长路径长度不超过最短路径长度的2倍)，可以说明红黑树虽然不像AVL一样是严格平衡的，
但平衡性能还是要比BST要好。其查找代价基本维持在O(logN)左右，但在最差情况下(最长路径是最短路径的
2倍少1)，比AVL要略逊色一点。

 - (2) 插入代价：
RBT插入结点时，需要旋转操作和变色操作。但由于只需要保证RBT基本平衡就可以了。因此插入结点最多只
需要2次旋转，这一点和AVL的插入操作一样。虽然变色操作需要O(logN)，但是变色操作十分简单，代价很小。

 - (3) 删除代价：
RBT的删除操作代价要比AVL要好的多，删除一个结点最多只需要3次旋转操作。

 - RBT 效率总结 :
&emsp;&emsp;查找效率最好情况下时间复杂度为O(logN)，但在最坏情况下比AVL要差一些，但也远远好于BST。
插入和删除操作改变树的平衡性的概率要远远小于AVL（RBT不是高度平衡的）。因此需要的旋转操作的可能性
要小，而且一旦需要旋转，插入一个结点最多只需要旋转2次，删除最多只需要旋转3次(小于AVL的删除操作所
需要的旋转次数)。虽然变色操作的时间复杂度在O(logN)，但是实际上，这种操作由于简单所需要的代价很小。

#### 1.4  红黑树比AVL树的优势在哪

&emsp;&emsp;首先红黑树是不符合AVL树的平衡条件的，即每个节点的左子树和右子树的高度最多差1的二叉查找树。但是提出了为节点
增加颜色，红黑是用非严格的平衡来换取增删节点时候旋转次数的降低，任何不平衡都会在三次旋转之内解决，而AVL是严格平衡树，
因此在增加或者删除节点的时候，根据不同情况，旋转的次数比红黑树要多。所以红黑树的插入删除效率更高！！！

【知乎回答：】
 - 1. 如果插入一个node引起了树的不平衡，AVL和RB-Tree都是最多只需要2次旋转操作，即两者都是O(1)；但是在删除node引起树
 的不平衡时，最坏情况下，AVL需要维护从被删node到root这条路径上所有node的平衡性，因此需要旋转的量级O(logN)，而RB-Tree
 最多只需3次旋转，只需要O(1)的复杂度。


### 2 应用场景
&emsp;&emsp;红黑树的应用比较广泛，主要是用它来存储有序的数据，它的时间复杂度是O(lgn)，效率非常之高。
例如，Java集合中的TreeSet、HashMap和TreeMap，C++ STL中的set、map，以及Linux虚拟内存的管理，都是通过红黑树去实现的。
**说明：**

&emsp;&emsp;完全二叉树，二叉查找树，平衡二叉树，红黑树都是从**内存**中查找数据，相对顺序存储、链表来说
能够提升查找效率，但如果是海量数据，无法一次性读到内存中时，这些数据结构就不在适用。
此时就需要研究从**磁盘**中查找海量数据的方法，就引入了b树和b+树
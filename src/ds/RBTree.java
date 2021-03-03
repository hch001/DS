package ds;

public class RBTree <T extends Comparable<? super T>>{
    private static class RBNode<T>{
        private int color; // 红0 黑1 默认是红
        RBNode<T> left,right,parent;
        T val;
        RBNode(T val){
            this.val=val;
        }
    }

    private RBNode<T> root,pre = new RBNode<>(null);
    RBTree(){
        root = null;
        pre.color=1;
    }

    public void add(T val){
        // 根节点始终为黑
        if(root==null) {
            pre.right = root = new RBNode<>(val);
            root.parent = pre;
            root.color=1;
        }
        else add(root,val);
        root = pre.right;
    }
    private void add(RBNode<T> node,T val){
        if(node==null) return;

        int cmp = 0;
        RBNode<T> cur = node,pre=node;
        while(cur!=null){
            cmp = val.compareTo(node.val);
            if(cmp<0) {
                pre = cur;
                cur = cur.left;
            }
            else if(cmp>0) {
                pre = cur;
                cur = cur.right;
            }
            else return ; // 匹配到就不改变直接返回
        }
        RBNode<T> newNode = new RBNode<>(val);

        if(cmp>0) pre.right = newNode;
        else pre.left = newNode;
        newNode.parent = pre;

        balanceAdd(newNode);
    }

    private void balanceAdd(RBNode<T> node) {
        RBNode<T> parent = node.parent,uncle;

        while(parent!=null&&parent.color==0){
            uncle = getUncle(node);

            // uncle存在且为红
            if(uncle!=null&&uncle.color==0){
                parent.color=uncle.color=1;
                parent.parent.color = 0;
                node = parent.parent;
                parent = node.parent;
            }
            // uncle不存在或者为黑
            else {
                RBNode<T> gParent = parent.parent,ggParent = gParent.parent;
                // 左
                if(parent==gParent.left){
                    // LR型 先转化为LL型
                    if(node==parent.right) gParent.left=LRotation(parent);

                    if(gParent==ggParent.left) {
                        ggParent.left = RRotation(gParent);
                        ggParent.left.color=1;

                        ggParent.left.left.color=ggParent.left.right.color=0;
                    }
                    else {
                        ggParent.right = RRotation(gParent);
                        ggParent.right.color=1;

                        ggParent.right.left.color=ggParent.right.right.color=0;
                    }
                }
                // 右
                else {
                    // RL型 先转化为RR型
                    if(node==parent.left) gParent.right = RRotation(parent);

                    if(gParent==ggParent.left){
                        ggParent.left = LRotation(gParent);
                        ggParent.left.color=1;

                        ggParent.left.left.color=ggParent.left.right.color=0;
                    }
                    else {
                        ggParent.right = LRotation(gParent);
                        ggParent.right.color = 1;

                        ggParent.right.left.color = ggParent.right.right.color=0;
                    }
                }
                break;
            }

            pre.right.color = 1;
        }
    }

    // 左旋
    private RBNode<T> LRotation(RBNode<T> node){
        RBNode<T> right = node.right;

        node.right = right.left;
        if(right.left!=null) right.left.parent=node;

        right.parent = node.parent;
        right.left = node;
        node.parent=right;
        return right;
    }

    // 右旋
    private RBNode<T> RRotation(RBNode<T> node){
        RBNode<T> left = node.left;

        node.left = left.right;
        if(left.right!=null) left.right.parent=node;

        left.parent = node.parent;
        left.right = node;
        node.parent=left;

        return left;
    }

    private RBNode<T> getUncle(RBNode<T> node){
        return (node.parent==node.parent.parent.left)?node.parent.parent.right:node.parent.parent.left;
    }

    public static void main(String[] args){
        RBTree<Integer> tree = new RBTree<>();
        for(int i:new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}) {
            tree.add(i);
            RBTree.show(tree.root);
            System.out.println("\n--------------");
        }
    }





    // 以下打印树的三个方法来源:https://blog.csdn.net/lenfranky/article/details/89645755
    public static int getTreeDepth(RBNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private static void writeArray(RBNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val+"."+currNode.color);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public static void show(RBNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }


}



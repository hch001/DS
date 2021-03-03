package ds;

public class AVLTree<T extends Comparable<? super T>> {
    private TreeNode<T> root;
    AVLTree(){
        root = null;
    }

    static private class TreeNode<T>{
        int height;
        TreeNode<T> left,right;
        T val;
        TreeNode(T val){
            this.val=val;
        }
        TreeNode(T val,int height) {
            this.val=val;
            this.height = height;
        }
    }

    // 添加节点
    public void add(T val) { root = add(root,val); }
    private TreeNode<T> add(TreeNode<T> node,T val){
        if(node==null)
            return new TreeNode<>(val,1);

        int cmp = val.compareTo(node.val);
        if(cmp<0)
            node.left = add(node.left,val);
        else if(cmp>0)
            node.right = add(node.right,val);
        else
            return node;

        node.height = getHeight1(node);

        int bf = getBF(node),leftBf = getBF(node.left),right = getBF(node.right);

        return reBalance(node,bf,leftBf,right);
    }

    public void remove(T val) {
        root = remove(root,val);
    }
    private TreeNode<T> remove(TreeNode<T> node,T val){
        if(node==null) return null;

        TreeNode<T> ans; // 用于重新平衡的节点
        int cmp = val.compareTo(node.val);
        if(cmp<0){
            node.left = remove(node.left,val);
            ans = node;
        }
        else if (cmp>0){
            node.right = remove(node.right,val);
            ans = node;
        }
        else {
            if(node.left==null||node.right==null){
                ans = (node.left!=null)?node.left:node.right;
            }
            else {
                TreeNode<T> tmp = node.left;
                while(tmp.right!=null) tmp = tmp.right;


                tmp.left = remove(node.left,tmp.val); // 递归，用于自底向上重新平衡，同时删除将删除tmp节点与其父节点的连接
                tmp.right = node.right; // 先处理完改动的子树(上面一行)，再修改另一侧子树(本行)
                ans = tmp;
            }
        }
        if(ans==null) return null;
        ans.height = getHeight1(ans);
        return reBalance(ans,getBF(ans),getBF(ans.left),getBF(ans.right));
    }

    // 平衡失衡节点并返回该节点
    private TreeNode<T> reBalance(TreeNode<T> node,int bf,int leftBf,int rightBf){
        if(bf>1){
            if(leftBf>=0) return RRotation(node);
            else return LRRotation(node);
        }
        else if(bf<-1){
            if(rightBf<=0) return LRotation(node);
            else return RLRotation(node);
        }
        return node;
    }

    // 右旋
    private TreeNode<T> RRotation(TreeNode<T> node){
        TreeNode<T> left = node.left;
        node.left = left.right;
        left.right = node;

        if(left.left!=null) left.left.height = getHeight1(left.left);
        left.right.height = getHeight1(left.right);
        left.height = getHeight1(left);

        return left;
    }

    // 左旋
    private TreeNode<T> LRotation(TreeNode<T> node){
        TreeNode<T> right = node.right;
        node.right = right.left;
        right.left = node;

        if(right.right!=null) right.right.height = getHeight1(right.right);
        right.left.height = getHeight1(right.left);
        right.height = getHeight1(right);

        return right;
    }

    // LR失衡
    private TreeNode<T> LRRotation(TreeNode<T> node){
        node.left = LRotation(node.left);
        return RRotation(node);
    }

    //RL失衡
    private TreeNode<T> RLRotation(TreeNode<T> node){
        node.right = RRotation(node.right);
        return LRotation(node);
    }

    // 获取平衡因子
    private int getBF(TreeNode<T> node){
        if(node==null) return 0;
        return getHeight1(node.left) - getHeight1(node.right);
    }


    // 失衡节点及其左右子节点的高度获取，减少了复杂度，O(1)
    private int getHeight1(TreeNode<T> node){
        if(node==null) return 0;
        int left = (node.left==null)?0:node.left.height;
        int right = (node.right==null)?0:node.right.height;
        return Math.max(left+1,right+1);
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        for(int i:new int[]{1,2,3,4,5,6,7,8,9,10,11,12}) {
            tree.add(i);
            AVLTree.show(tree.root);
        }
        tree.remove(4);
        tree.remove(9);
        AVLTree.show(tree.root);
        tree.remove(1);
        AVLTree.show(tree.root);

    }

    // 以下打印树的三个方法来源:https://blog.csdn.net/lenfranky/article/details/89645755
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val+"."+currNode.height);

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

    public static void show(TreeNode root) {
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

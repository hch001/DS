package ds;


public class BSTree<T extends Comparable<? super T>> extends BinTree<T>{

    public final void add(final T value){
        root = add(root,new TreeNode<>(value));
    }
    // java没有指针的引用或者指针的指针来改变实参本身的值，只能通过赋值
    private TreeNode<T> add(TreeNode<T> newNode,TreeNode<T> node){
        if(node==null) node = newNode;

        int cmp = newNode.val.compareTo(node.val);
        if(cmp<0) node.left = add(newNode,node.left);
        else if(cmp>0) node.right = add(newNode,node.right);

        return node;
    }

    public boolean removeNode(T val){
        if(root==null) return false;
        TreeNode<T> pre = new TreeNode<>(),ans=pre;
        pre.right = root;
        TreeNode<T> target = root;
        char turn = 'r'; // 记录方向
        while(target!=null&&!target.val.equals(val)){
            int cmp = val.compareTo(target.val);
            pre = target;
            if(cmp>0){
                target = target.right;
                turn = 'r';
            }
            else {
                target=target.left;
                turn = 'l';
            }
        }
        if(target==null) return false;

        // 左右均为空
        if(target.left==null&&target.right==null){
            if(turn=='l') pre.left=null;
            else pre.right=null;
        }
        // 左子树空
        else if(target.left==null){
            if(turn=='l') pre.left=target.right;
            else pre.right = target.right;
        }
        else if(target.right==null){
            if(turn=='l') pre.left=target.left;
            else pre.right=target.left;
        }
        //均不为空
        else {
            // 左子树代替删除的节点的位置
            if(turn=='l') pre.left = target.left;
            else pre.right = target.left;

            // 右子树作为左子树的最大节点的右子树
            TreeNode<T> tmp = target.left;
            while(tmp.right!=null) tmp=tmp.right;
            tmp.right = target.right;
        }
        // 改变根节点，因为有可能删除根节点
        root=ans.right;
        return true;
    }

    public TreeNode<T> search(T val){
        if(root==null) return null;
        TreeNode<T> target = root;
        while(target!=null&&target.val.equals(val)){
            int cmp = val.compareTo(target.val);
            if(cmp>0) target=target.right;
            else target=target.left;
        }
        return target;
    }


    public static void main(String[] args){
        BSTree<Integer> tree = new BSTree<>();
        for(int i:new int[]{1})
            tree.add(i);
        System.out.print(tree.isComplete());

    }

}

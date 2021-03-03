package ds;

public class BinTree <T>{
    protected TreeNode<T> root;

    BinTree() { root=null; }

    static protected final class TreeNode<T>{
        T val;
        TreeNode<T> left,right;
        TreeNode(){
            left = right = null;
        }

        TreeNode(T val){
            this.val = val;
            left = right =null;
        }
    }

    public final void preOrder(){
        preOrder1(root);
    }
    private void preOrder(TreeNode<T> node) {
        if(node==null) return;
        
        System.out.print(node.val+" ");
        preOrder(node.left);
        preOrder(node.right);
    }
    private void preOrder1(TreeNode<T> node){
        if(node==null) return;
        DuLinkedList<TreeNode<T>> stack = new DuLinkedList<>();
        TreeNode<T> n=node;

        while(n!=null||!stack.isEmpty()){
            if(n!=null){
                System.out.print(n.val+" ");
                stack.push(n);
                n=n.left;
            }
            else {
                   n=stack.pop();
                   n=n.right;
            }
        }
        System.out.println();
    }

    public final void inOrder(){
        inOrder1(root);
    }
    private void inOrder(TreeNode<T> node){
        if(node==null) return;
        
        inOrder(node.left);
        System.out.print(node.val+" ");
        inOrder(node.right);
    }
    private void inOrder1(TreeNode<T> node){
        if(node==null) return;
        DuLinkedList<TreeNode<T>> stack = new DuLinkedList<>();
        TreeNode<T> n = node;

        while(n!=null||!stack.isEmpty()){
            if(n!=null){
                stack.push(n);
                n=n.left;
            }
            else{
                n = stack.pop();
                System.out.print(n.val+" ");
                n=n.right;
            }
        }
        System.out.println();
    }


    public final void postOrder(){
        postOrder1(root);
    }
    private void postOrder(TreeNode<T> node){
        if(node==null) return;
        
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val+" ");
    }
    private void postOrder1(TreeNode<T> node){
        if(node==null) return;
        DuLinkedList<TreeNode<T>> stack  = new DuLinkedList<>();
        TreeNode<T> n = node,last=null;

        while(n!=null||!stack.isEmpty()){
            if(n!=null){
                stack.push(n);
                n=n.left;
            }
            else {
                n=stack.peek();
                if(n.right!=null&&last!=n.right){
                    n=n.right;
                    stack.push(n);
                    n=n.left;
                }
                else {
                    n=stack.pop();
                    System.out.print(n.val+" ");
                    last=n;
                    n=null;
                }
            }
        }
    }

    public final void bfs(){
        DuLinkedList<TreeNode<T>> queue = new DuLinkedList<>();
        queue.offer(root);
        TreeNode<T> cur;
        while(!queue.isEmpty()){
            cur = queue.poll();
            System.out.print(cur.val+" ");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        System.out.println();
    }

    public final void dfs(){
        DuLinkedList<TreeNode<T>> stack = new DuLinkedList<>();
        stack.push(root);
        TreeNode<T> cur;
        while(!stack.isEmpty()){
            cur = stack.pop();
            System.out.print(cur.val+" ");
            stack.push(cur.right);
            stack.push(cur.left);
        }
        System.out.println();

    }

    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(TreeNode<T> node){
        if(node==null) return 0;
        int left = getHeight(node.left);
        int right = getHeight(node.right);

        return (left>right)?(left+1):(right+1);
    }

    public boolean createByPreAndInOrderArray(T[] pre,T[] in) {
        if(pre==null||in==null||in.length!=pre.length) return false;
        return null != (root = createByPreAndInOrderArray(pre,in,0,pre.length-1,0,in.length-1));
    }
    private TreeNode<T> createByPreAndInOrderArray(T[] pre,T[] in,int preStart,int preEnd,int inStart,int inEnd) {
        if(inStart==inEnd) return new TreeNode<>(in[inStart]);
        if(inStart>inEnd) return null;

        int mid = inStart;
        T midVal = pre[preStart];
        TreeNode<T> ret = new TreeNode<>(midVal);
        while(mid<=inEnd&&midVal!=in[mid])
            mid++;
        if(mid>inEnd) {
            System.out.println("数组格式有误");
            return null;
        }

        ret.left = createByPreAndInOrderArray(pre,in,preStart+1,preEnd,inStart,mid-1);

        ret.right =createByPreAndInOrderArray(pre,in,preStart+mid-inStart+1,preEnd,mid+1,inEnd);

        return ret;
    }

    public boolean isComplete(){
        if(root==null) return true;

        java.util.LinkedList<TreeNode<T>> queue = new java.util.LinkedList<>();
        queue.offer(root);
        TreeNode<T> n;

        while(!queue.isEmpty()){
            n = queue.poll();
            if(n!=null){
                queue.offer(n.left);
                queue.offer(n.right);
            }
            else{
                while(!queue.isEmpty()){
                    n = queue.poll();
                    if(n!=null) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        BinTree<Integer> tree = new BinTree<>();
        Integer[] pre = new Integer[]{10,7,9,20,22};
        Integer[] in = new Integer[]{7,9,10,20,22};

        System.out.println(tree.createByPreAndInOrderArray(pre,in));
        tree.postOrder();
    }
}

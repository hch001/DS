package ds;

public class HuffManTree {
    static private class HNode{
        HNode parent,left,right;
        String name,code;
        int weight;
        HNode(String name,int weight,HNode parent,HNode left,HNode right){
            this.name=name;
            this.parent=parent;
            this.left=left;
            this.right=right;
            this.weight=weight;
        }
    }
    HNode[] table;
    private HNode root;

    private int[] selectMin(HNode[] nodes,int length){

        int[] ans = new int[2];
        int a=-1,b=-1;

        for(int i=0;i<length;i++)
            if(nodes[i].parent==null) {
                a = i;
                break;
            }
        for(int i=0;i<length;i++)
            if(nodes[i].parent==null&&i!=a){
                b=i;
                break;
            }

        for(int i=0;i<length;i++){
            if(nodes[i].parent==null&&nodes[i].weight<nodes[a].weight&&i!=b)
                a=i;
        }
        for(int i=0;i<length;i++){
            if(nodes[i].parent==null&&nodes[i].weight<nodes[b].weight&&i!=a)
                b=i;
        }
        ans[0]=a;
        ans[1]=b;
        return ans;
    }

    public boolean create(String[] names,int[] weights){
        if(weights==null||weights.length<=1) return false;

        if(names==null||names.length!=weights.length) {
            names=new String[weights.length];
            for(int i=0;i<names.length;i++){
                names[i]=weights[i]+"";
            }
        }
        table = new HNode[2*weights.length-1];

        for(int i=0;i<weights.length;i++){
            table[i]=new HNode(names[i],weights[i],null,null,null);
        }
        for(int i=weights.length;i<table.length;i++)
            table[i]=new HNode("",0,null,null,null);

        int[] pos;
        for(int i=weights.length;i<table.length;i++){
            pos = selectMin(table,i);
            table[pos[0]].parent=table[i];
            table[pos[1]].parent=table[i];
            table[i].left=table[pos[0]];
            table[i].right=table[pos[1]];
            table[i].weight=table[i].left.weight+table[i].right.weight;
        }
        root=table[table.length-1];
        return true;
    }

    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(HNode node){
        if(node==null) return;
        System.out.print(node.name+"("+node.weight+") ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 编码,给code字段赋值
    public void encode(){
        encode(root,"");
    }
    private void encode(HNode root,String s){
        if(root==null) return;
        if(root.parent==null) root.code=s;
        else root.code=  root.parent.code+s;
        encode(root.left,"0");
        encode(root.right,"1");
    }

    // 查询编码结果
    public String searchCode(String name){return searchCode(root,name);}
    private String searchCode(HNode node,String name){
        if(node==null) return "";
        if(node.name.equals(name)) return node.code;

        return searchCode(node.left,name)+searchCode(node.right,name);
    }

    public void printCode(){
        if(root==null) return;
        for(int i=0;i<(table.length+1)/2;i++)
            System.out.println(table[i].name+":"+table[i].code);
    }

    public static void main(String[]args){
//        HuffManTree tree = new HuffManTree();
//        int[] weights = new int[]{12,434,1,4,22};
//        tree.create(new String[]{"a","b","c","d","e"},weights);
//        tree.encode();
//        tree.printCode();

    }


}

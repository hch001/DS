package ds;


import java.util.NoSuchElementException;


public class DuLinkedList<T> {
    private DuNode<T> head;
    private DuNode<T> first,last;

    DuLinkedList(){
        head = new DuNode<>();
    }

    static private class DuNode<T>{
        T val;
        DuNode<T> next;
        DuNode<T> prior;

        DuNode() {
            next = null;
            prior = null;
        }

        DuNode(T val) {
            this.val = val;
            next = null;
            prior = null;
        }
    }

    public boolean addFirst(T val) { return addFirst(new DuNode<>(val));}
    private boolean addFirst(DuNode<T> newNode){
        if(newNode == null || newNode.val == null) return false;
        if(isEmpty()) return addLast(newNode);

        newNode.next = head.next;
        head.next.prior = newNode;
        head.next = newNode;
        newNode.prior = head;

        first = head.next;
        return true;
    }

    public boolean addLast(T val) { return addLast(new DuNode<>(val));}
    private boolean addLast(DuNode<T> newNode){
        if(newNode == null || newNode.val==null) return false;

        DuNode<T> cur = head;
        while(cur.next!=null) cur = cur.next;

        cur.next = newNode;
        newNode.prior = cur;
        
        last = newNode;
        if(last==head.next) first = last;
        return true;
    }

    public boolean isEmpty(){
        return head.next == null;
    }

    public T getFist(){ return first.val;}
    public T getLast(){ return last.val;}

    // 链表的第一个元素为队列的头部
    public T poll(){
        if(first == null) throw new NoSuchElementException();
        DuNode<T> ret = head.next;
        head.next = head.next.next;
        first = head.next;
        if(first==null) last=null;
        return ret.val;
    }

    public boolean offer(T val){
        if(last==null||val==null)
            return addLast(val);
        last.next = new DuNode<>(val);
        last = last.next;
        return true;
    }

    public T peek(){
        return (first==null)?null:first.val;
    }

    // 链表的第一个元素为栈顶
    public boolean push(T val){
        return addFirst(val);
    }

    public T pop(){
        if(first==null) throw new NoSuchElementException();
        head.next = head.next.next;
        T ret = getFist();
        first = head.next;
        if(first==null) last = null;
        return ret;
    }

    @Override
    public String toString(){
        String s ="";
        for(DuNode<T> cur = head.next;cur!=null;cur = cur.next){
            s=cur.val+" ";
        }
        return s;
    }


    public static void main(String[] args){



    }
}



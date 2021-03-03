package ds;


import java.util.*;

public class LinkedList <T>{
    private Node<T> head ;

    LinkedList(){
        head = new Node<T>();
    }

    static private class Node<T>{
        T val;
        Node<T> next;

        Node(){next=null;}
        Node(T val){this.val=val;next=null;}
    }

    public boolean addFirst(T val) { return addFirst(new Node<>(val));}
    private boolean addFirst(Node<T> newNode){
        if(newNode==null||newNode.val==null) return false;

        newNode.next = head.next;
        head.next = newNode;
        return true;
    }


    public boolean addLast(T val) { return addLast(new Node<>(val)); }
    private boolean addLast(Node<T> newNode){
        if(newNode==null || newNode.val==null) return false;

        Node<T> tmp = head;
        while(tmp.next!=null){
            tmp = tmp.next;
        }
        tmp.next = newNode;
        return true;
    }


    public boolean insert(int pos,T val) { return insert(pos,new Node<T>(val)); }
    private boolean insert(int pos,Node<T> newNode){
        if(newNode==null||newNode.val==null) return false;

        Node<T> tmp = head;
        int i = 0;
        while(i<pos&&tmp!=null){
            tmp=tmp.next;
            i++;
        }

        if(tmp==null) return false;

        newNode.next = tmp.next;
        tmp.next = newNode;
        return true;
    }

    public boolean removeAtIndex(int index){
        Node<T> tmp = head;
        int i=0;
        while(i<index&&tmp.next!=null){
            tmp = tmp.next;
            i++;
        }

        if(tmp.next==null) return false;
        tmp.next=tmp.next.next;
        return true;
    }

    public boolean removeByVal(T val){
        Node<T> tmp = head;
        while(tmp.next!=null&&tmp.next.val.equals(val)){
            tmp = tmp.next;
        }
        if(tmp.next==null) return false;

        tmp.next = tmp.next.next;
        return true;
    }

    public void printAll(){
        for(Node<T> tmp = head.next;tmp!=null;tmp=tmp.next){
            System.out.print(tmp.val+" ");
        }
        System.out.println();
    }


    public static void main(String[] args){
//        LinkedList<Integer> list = new LinkedList<>();
//        list.addFirst(10);
//        list.addFirst(20);
//        list.addFirst(30);
//        list.addFirst(100);
//        list.printAll();

        Integer[][] a=new Integer[10][];
        List<Integer[]> l = new ArrayList<>();
        l.add(new Integer[]{1,2});
        l.toArray(a);
        System.out.println(a[0][0]);
        int b= 0b1111;



    }





}

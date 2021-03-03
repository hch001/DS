package ds;

import java.util.Arrays;

public class Heap {
    private boolean bigRoot = true;
    private int capacity = 2<<4-1;
    private final int[] table;
    private int size = 0;

    Heap(){
        table = new int[capacity+1];
    }
    Heap(int capacity,boolean bigRoot){
        this.capacity = capacity;
        table = new int[capacity+1];
        this.bigRoot = bigRoot;
    }

    // 插入一个节点
    public boolean insert(int val){
        if(isFull()) return false;

        size++;
        table[size] = val;
        
        heapifyUp(table,1,size);
        return true;
    }
    // 自下而上堆化
    private void heapifyUp(int[] table,int startIdx,int idx){
        for(int i=idx;i>=startIdx;i=i/2){
            if(bigRoot){
                if(table[i]>table[i/2]) swap(table,i,i/2);
            }
            else {
                if(table[i]<table[i/2]) swap(table,i,i/2);
            }
        }        
    }

    // 删除顶点
    public boolean remove(){
        if(isEmpty()) return false;

        table[1]=table[size];
        size--;
        heapifyDown(table,1,size);
        return true;
    }
    // 自上而下堆化
    private void heapifyDown(int[] table,int startIdx,int endIdx){
        int finalIdx=startIdx;
        while(true){
            if(bigRoot){
                if(startIdx*2<=endIdx&&table[startIdx]<table[startIdx*2]) finalIdx = startIdx*2;
                if((startIdx*2+1)<=endIdx&&table[finalIdx]<table[startIdx*2+1]) finalIdx = startIdx*2+1;
            }
            else {
                if(startIdx*2<=endIdx&&table[startIdx]>table[startIdx*2]) finalIdx = startIdx*2;
                if((startIdx*2+1)<=endIdx&&table[finalIdx]>table[startIdx*2+1]) finalIdx = startIdx*2+1;
            }

            if(finalIdx==startIdx) break;
            swap(table,startIdx,finalIdx);
            startIdx = finalIdx;
        }
    }

    public boolean build(int[] values){
        if(values.length>capacity) return false;

        System.arraycopy(values, 0, table, 1, values.length);

        size = values.length;

        for(int i=size/2;i>0;i--)
            heapifyDown(table,i,size);
        return true;
    }

    public void printAll(){
        for(int i=1;i<=size;i++){
            System.out.print(table[i]+" ");
        }
        System.out.println();
    }

    public boolean isFull() { return size>=capacity; }

    public boolean isEmpty() { return size<=0; }

    private void swap(int[] table,int a,int b){
        int tmp = table[a];
        table[a]=table[b];
        table[b]=tmp;
    }

    public static void main(String[] args){
        Heap heap = new Heap(50,false);
        int[] values={1,3,6,20,-1,0,-20,-66,2,-4};
        heap.build(values);
        heap.printAll();
    }
}

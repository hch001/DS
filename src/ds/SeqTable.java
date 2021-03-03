package ds;


public class SeqTable<T> {
    
    static int DEFAULT_CAPACITY = 16;
    static int MAX_CAPACITY = 1024;
    private Object[] table;
    private int capacity;
    private int size = 0;
    
    SeqTable(){
        this.table = new Object[DEFAULT_CAPACITY];
        this.capacity=DEFAULT_CAPACITY;
    }

    SeqTable(int initSize){
        if(initSize<0||initSize>MAX_CAPACITY) {
            this.table = null;
            return;
        }

        this.table = new Object[initSize];
        this.capacity=initSize;
    }

    public void printAll(){
        for(int i=0;i<size;i++){
            System.out.print(table[i]+" ");
        }
        System.out.println();
    }

    public boolean replaceAt(int pos,T val){
        if(!inRange(pos)) return false;
        table[pos] = val;
        return true;
    }

    public T getVal(int pos){
        if(!inRange(pos)) return null;
        return (T)table[pos];
    }

    public boolean addAtIndex(int index,T val){
        if(index>size||index<0||isFull()) return false;

        for(int i = capacity-1;i>index;i--){
            table[i]=table[i-1];
        }
        table[index]=val;
        size++;
        return true;
    }

    public boolean removeAtIndex(int index){
        if(!inRange(index)||isEmpty()) return false;
        for(int i = index;i<size-1;i++){
            table[i]=table[i+1];
        }
        size--;
        return true;
    }

    public boolean isFull() { return this.size==this.capacity; }
    public boolean isEmpty() { return this.size==0; }
    private boolean inRange(int index) { return index>=0&&index<size; }


    public static void main(String[] args){
        SeqTable<Integer> table = new SeqTable<>(5);

        table.addAtIndex(0,20);
        table.addAtIndex(0,2);
        table.addAtIndex(1,3);
        table.addAtIndex(3,5);
        table.addAtIndex(1,25);
        table.printAll();
        table.removeAtIndex(2);
        table.printAll();
        table.replaceAt(4,1);
        table.printAll();
        System.out.println(table.getVal(4));
    }
}

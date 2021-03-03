package sort;

import java.util.Arrays;

public final class Sort {


    public static void main(String[] args){
        int[] array = {99, 5, 69, 33, 56, 13, 22, 55, 77, 48, 12, 88, 2,69,99};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));


    }

    /**
     * n-1轮，选择未被排序的部分的最小值将其放置在正确的位置
     */
    public static void selectSort(int[] array){
        if(array==null) return;

        for(int i=0;i<array.length-1;i++){
            int minIdx = i;
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[minIdx])
                    minIdx = j;
            }
            swap(array,i,minIdx);
        }
    }

    /**
     * n-1轮，将数组分为已排序和未排序的部分，未排序的部分每次选取一个值依次交换到排序部分的合适位置
     */
    public static void insertionSort(int[] array){
        if(array==null) return;

        for(int i=1;i<array.length;i++)
            for(int j=i;j>0;j--)
                if(array[j-1]>array[j])
                    swap(array,j-1,j);
                else break;
    }

    /**
     * 与插入排序类似，将间隔定为gap而不是1
     */
    public static void shellSort(int[] array){
        if(array==null) return;

        int size = array.length;
        for(int gap=size;gap>=1;gap>>=1)
            for(int i=gap;i<size;i++)
                for(int j=i;j-gap>=0;j-=gap)
                    if(array[j-gap]>array[j])
                        swap(array,j-gap,j);
                    else break;
    }

    /**
     * 最基础的排序，n-1轮，每次将数组前面部分中大的值向右交换，直到最后
     */
    public static void bubbleSort(int[] array){
        if(array==null) return;

        for(int i=1;i<array.length;i++)
            for(int j=0;j<array.length-1-i;j++)
                if(array[j]>array[j+1])
                    swap(array,j,j+1);
    }

    // left right 均为闭区间
    public static void mergeSort(int[] array,int left,int right){
        if(left>=right||array==null) return;

        int mid = (left+right)/2;
        mergeSort(array,left,mid);
        mergeSort(array,mid+1,right);
        ontTimeMerge(array,left,right);
    }

    private static void ontTimeMerge(int[] array,int left,int right){
        int mid = (left+right)/2;
        int[] L = Arrays.copyOfRange(array,left,mid+1);
        int[] R = Arrays.copyOfRange(array,mid+1,right+1);

        int i=0,j=0;
        while(left<=right){
            if(j>=R.length||(i<L.length&&L[i]<R[j]))
                array[left++]=L[i++];
            else
                array[left++]=R[j++];
        }
    }

    public static void quickSort(int[] array,int start,int end){
        if(array==null||array.length==0||start>=end) return;

        int pivot = array[start],i=start,j=end;

        /*这里一定是小于号，如果是等于的话，设想一个已排序的数组，j将会到达-1，indexOutOfRange*/
        while(i<j){
            while(i<j&&array[j]>=pivot)
                j--;
            array[i]=array[j];
            while(i<j&&array[i]<=pivot)
                i++;
            array[j]=array[i];
        }
        array[i] = pivot;
        quickSort(array,start,i-1);
        quickSort(array,i+1,end);
    }

    public static void heapSort(int[] array){
        if(array==null||array.length==0) return;

        // i是闭区间
        for(int i=array.length;i>0;i--){
            HeapSort.build(array,i);
            swap(array,0,i-1);
        }
    }

    private static class HeapSort{

        // length 闭区间
        private static void build(int[] array,int length){
            for(int i=length/2-1;i>=0;i--){
                heapify(array,i, length);
            }
        }

        // end 是闭区间
        private static void heapify(int[] array,int start,int end){
            int left = start*2+1,right=start*2+2,maxIdx=start;

            if(left<end&&array[left]>array[maxIdx]) maxIdx = left;
            if(right<end&&array[right]>array[maxIdx]) maxIdx = right;

            if(maxIdx!=start){
                swap(array,start,maxIdx);
                // 向下搜索
                heapify(array,maxIdx,end);
            }
        }

    }

    public static void swap(int[] array,int i,int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}



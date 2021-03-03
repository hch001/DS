import java.util.Arrays;

public class BinarySearch {
    /**
     *
     * @param target element whose index in the array is to be located
     * @return the index of {@code target} in the array if {@code target } is in the array else -1
     */
    public static int binarySearch(int[] array,int target){
        if(array==null||array.length==0) return -1;

        int left=0,right=array.length-1,mid;
        while(left<=right){
            mid=(left+right)>>1;
            if(target<array[mid])
                right=mid-1;
            else if(target>array[mid])
                left=mid+1;
            else return mid;
        }
        return -1;
    }

    /**
     *
     * @param target element whose index in the array is to be located
     * @return the left boundary of target's index in the array if target is in the array else -1
     */
    public static int LBinarySearch(int[] array,int target){
        if(array==null||array.length==0) return -1;

        int left=0,right=array.length-1,mid;
        while(left<=right){
            mid=(left+right)>>1;
            if(target>array[mid])
                left=mid+1;
            else
                right=mid-1;
        }
        if(left>=array.length||array[left]!=target) return -1;
        return left;
    }

    /**
     *
     * @param target element whose index in the array is to be located
     * @return the right boundary of target's index in the array if target is in the array else -1
     */
    public static int RBinarySearch(int[] array,int target){
        if(array==null||array.length==0) return -1;

        int left=0,right=array.length-1,mid;
        while(left<=right){
            mid=(left+right)>>1;
            if(target<array[mid])
                right=mid-1;
            else
                left=mid+1;
        }
        if(right<0||array[right]!=target) return -1;
        return right;
    }

    public static void main(String[] args){
        int[] array={3,1,5,6,22,-3,0};
        System.out.println(Arrays.toString(array));
    }

}

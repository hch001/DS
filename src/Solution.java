
import java.util.*;

public class Solution {

    public interface Converter{
        private static void func2(){};
        Integer Convert(String from);


    }

    public static void func(Converter converter){
    }


    public static void main(String[] args) {
        int[] array={10,3,4,5,2,100,55};
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }



    public static void insertSort(int[] array){
        for(int i=0;i<array.length-1;i++)
            for(int j=i+1;j>0;j--)
                if(array[j-1]>array[j])
                    swap(array,j-1,j);
                else break;
    }

    public static void shellSort(int[] array){
        for(int gap=array.length;gap>0;gap>>=1)
            for(int i=0;i<array.length-gap;i+=gap)
                for(int j=i+gap;j-gap>=0;j-=gap)
                    if(array[j-gap]>array[j])
                        swap(array,j-gap,j);
    }














    // 快速幂
//    public static int quickPower(int base,int power){
//        int res=1;
//
//        while(power!=0){
//            if(power%2==1)
//                res*=base;
//            base*=base;
//            power>>=1;
//        }
//        return res;
//    }

    public static int quickPower(int base,int power){
        int res=1;
        while(power>0){
            if(power%2==1) res*=base;
            base*=base;
            power>>=1;
        }
        return res;
    }

    public static void swap(int[] array,int i,int j){
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

}
enum SeasonEnum { //上述枚举类的class文件
    SPRING("春天"),
    SUMMER("夏天"),
    FALL("秋天"),
    WINTER("冬天");

    SeasonEnum(String name) { //默认的构造函数
    }

}
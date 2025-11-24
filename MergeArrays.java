import java.util.Scanner;

public class MergeArrays {
    public static int[] mergeArrays(int[] array1,int[] array2){
        int len1 = array1.length;
        int len2 = array2.length;
        int[] mergedArray = new int[len1+len2];
        int i=0,j=0,k=0;
        while(i<len1&&j<len2){
            if(array1[i]<array2[j]){
                mergedArray[k++] = array1[i++];
            }
            else{
                mergedArray[k++] = array2[j++];
                }
        }
        while(i<len1){
            mergedArray[k++] = array1[i++];
        }
        while(j<len2){
            mergedArray[k++] = array2[j++];
        }
        return mergedArray;
    }
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数组的长度：");
        int len1 = sc.nextInt();
        int[] array1 = new int[len1];
        System.out.println("请输入第一个数组的元素：");
        for(int i=0;i<len1;i++){
            array1[i]=sc.nextInt();
        }
        System.out.println("请输入第二个数组的长度：");
        int len2 = sc.nextInt();
        int[] array2 = new int[len2];
        System.out.println("请输入第二个数组的元素：");
        for(int i=0;i<len2;i++){
            array2[i]=sc.nextInt();
        }
        int[] mergedArray = mergeArrays(array1,array2);
        System.out.println("合并后的数组为：");
        for(int num:mergedArray){
            System.out.print(num+" ");
        }
        sc.close();
    }
}

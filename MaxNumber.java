import java.util.Scanner;

public class MaxNumber {
    public  static <T extends Comparable<T>> T Max(T[] array){
        if(array.length==0){
            return null;
        }
        T max=array[0];
        for(T num:array){
            if(num.compareTo(max)>0){
                max=num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请选择要排序的数组的类型：1.整数数组 2.浮点数数组 3.字符串数组");
            int n = sc.nextInt();
            if(n!=1&&n!=2&&n!=3){
                System.out.println("输入错误！");
                sc.close();
                break;
            }
            else{
                switch(n){
                    case 1:
                        System.out.println("请输入整数数组的长度：");
                        int len = sc.nextInt();
                        Integer[] intArray = new Integer[len];
                        System.out.println("请输入整数数组的元素：");
                        for(int i=0;i<len;i++){
                            intArray[i]=sc.nextInt();
                        }
                        System.out.println("整数数组的最大值为："+Max(intArray));
                        break;
                    case 2:
                        System.out.println("请输入浮点数数组的长度：");
                        len = sc.nextInt();
                        Double[] doubleArray = new Double[len];
                        System.out.println("请输入浮点数数组的元素：");
                        for(int i=0;i<len;i++){
                            doubleArray[i]=sc.nextDouble();
                        }
                        System.out.println("浮点数数组的最大值为："+Max(doubleArray));
                        break;
                    case 3:
                        System.out.println("请输入字符串数组的长度：");
                        len = sc.nextInt();
                        String[] strArray = new String[len];
                        System.out.println("请输入字符串数组的元素：");
                        for(int i=0;i<len;i++){
                            strArray[i]=sc.next();
                        }
                        System.out.println("字符串数组的最大值为："+Max(strArray));
                        break;
                }
            }

        }
    }
}

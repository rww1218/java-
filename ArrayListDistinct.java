import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDistinct {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList <Integer>();
        System.out.println("请输入一系列的整数：");
        while(sc.hasNextInt()){
            int a = sc.nextInt();
            if(!list.contains(a)){
                list.add(a);
            }
        }
        System.out.println("不重复的整数有："+list);
        sc.close();
    }
}

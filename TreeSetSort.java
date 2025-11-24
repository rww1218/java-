import java.util.Scanner;
import java.util.TreeSet;

public class TreeSetSort {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc=new Scanner(System.in);
        TreeSet<Integer> set=new TreeSet<>();
        while(sc.hasNextInt()){
            set.add(sc.nextInt());
        }
        System.out.println("排序后的结果为："+set);
    }
}

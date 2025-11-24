import java.util.ArrayList;
import java.util.Scanner;

public class ArrayReverse {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        ArrayList<Integer> list=new ArrayList<Integer>();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        while(n-->0){
            int a=sc.nextInt();
            list.add(a);
        }
        for(int i=list.size()-1;i>=0;i--){
            System.out.print(list.get(i)+" ");
        }
    }
}

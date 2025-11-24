import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if((n%4==0&&n%100!=0)||(n%400==0)){
            System.out.println("是闰年");
        }else{
            System.out.println("不是闰年");
        }
    }
}
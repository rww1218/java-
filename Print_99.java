import java.util.Scanner;

public class Print_99 {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        for(int i=1;i<=9;i++){
            for(int j=1;j<=i;j++) {
                int product = i * j;
                System.out.print(i + "*" + j + "=" + product + " ");
            }
            System.out.print("\n");
        }
    }
}
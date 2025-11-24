import java.util.Scanner;
public class SimpleCalculator {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char ch = sc.next().charAt(0);
        switch (ch) {
            case '+':
                System.out.println(n + m);
                break;
            case '-':
                System.out.println(n - m);
                break;
            case '*':
                System.out.println(n * m);
                break;
            case '/':
                if (m != 0) {
                    System.out.println(n / m);
                } else {
                    System.out.println("Error: Division by zero");
                }
                break;
            default:
                System.out.println("Error: Invalid input");
                break;
        }

        return ;
    }
}

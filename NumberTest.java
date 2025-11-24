import java.util.Scanner;

public class NumberTest {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个整数");
        int num = sc.nextInt();
        try{
            if(num<0){
                throw new NegativeNumberException("输入的值为负数:"+num);
            }
            System.out.println("符合要求，输入的值为:"+num);
        }
        catch(NegativeNumberException e){
            System.out.println(e.getMessage());
        }
    }
}

class NegativeNumberException extends Exception{
    public NegativeNumberException(String message){
        super("输入异常："+message);
    }
}
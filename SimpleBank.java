import java.util.Scanner;

public class SimpleBank {
    public static void main(String[] args){
        System.out.println("计科六班-任雯-2405010624");
        BankAccount account = new BankAccount();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入存款金额");
        double money1 = sc.nextDouble();
        account.deposit(money1);
        System.out.println("当前余额为：" + account.checkBalance());
        System.out.println("请输入取款金额");
        double money2 = sc.nextDouble();
        account.withdraw(money2);
        System.out.println("当前余额为：" + account.checkBalance());
    }
}

class BankAccount{
    private double money;
    public BankAccount(){
        this.money = 0;
    }
    public double  getMoney(){
        return this.money;
    }
    public void setMoney(double money){
        this.money = money;
    }

    public void deposit(double money){
        this.money += money;
        System.out.println("存款成功");
    }
    public void withdraw(double money){
        if(this.money < money){
            System.out.println("余额不足");
        }
        else{
            this.money -= money;
            System.out.println("取款成功");
        }
    }
    public double checkBalance(){
        return this.money;
    }
}

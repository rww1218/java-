import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class BankAccount1 {
    private String accountId;
    private String username;
    private double balance;
    public BankAccount1(String accountId, String username, double balance) {
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
    }
    public String getAccountId() {
        return accountId;
    }
    public String getUsername() {
        return username;
    }
    public double getBalance() {
        return balance;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "BankAccount1{" +
                "accountId='" + accountId +
                ", username='" + username +
                ", balance=" + balance +
                '}';
    }

    public void deposit(double amount) {
        if(amount <= 0){
            System.out.println("存款金额不能小于等于0！");
            return;
        }
        this.balance += amount;
    }
    public void withdraw(double amount) {
        if(amount <= 0){
            System.out.println("取款金额不能小于等于0！");
            return;
        }
        else if(amount > this.balance){
            System.out.println("余额不足！");
            return;
        }
        else{
            this.balance -= amount;
        }
    }
    public String toFileString(){
        return accountId + "," + username + "," + balance;
    }
    public static BankAccount1 getBankAccount(String accountId){
        if(accountId == null){
            return null;
        }
        String[] split = accountId.split(",");
        if(split.length != 3){
            return null;
        }
        try{
            double balance = Double.parseDouble(split[2]);
        }
        catch(NumberFormatException e){
            return null;
        }
        return new BankAccount1(split[0], split[1], Double.parseDouble(split[2]));
    }
}

public class BankAccountSystem {
    private static final String filePath = "bank_accounts.txt";
    private  HashMap<String, BankAccount1> bankAccounts;
    private  Scanner scanner ;
    private List<BankAccount1> currentCreatedBankAccount=new ArrayList<BankAccount1>();
    public BankAccountSystem() {
        bankAccounts = new HashMap<>();
        scanner = new Scanner(System.in);
        loadBankAccounts();
    }
    void createBankAccount() {
        System.out.print("请输入账户ID：");
        String accountId = scanner.nextLine();
        if(bankAccounts.containsKey(accountId)){
            System.out.println("账户ID已存在！");
            return;
        }
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入初始余额：");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        if(balance <= 0){
            System.out.println("初始余额不能小于等于0！");
            return;
        }
        BankAccount1 bankAccount = new BankAccount1(accountId, username, balance);
        bankAccounts.put(accountId, bankAccount);
        currentCreatedBankAccount.add(bankAccount);
        System.out.println("账户创建成功！"+bankAccount.toString());
    }
    void deposit() {
        System.out.print("请输入账户ID：");
        String accountId = scanner.nextLine();
        BankAccount1 bankAccount = bankAccounts.get(accountId);
        if(bankAccount == null){
            System.out.println("账户ID不存在！");
            return;
        }
        System.out.print("请输入存款金额：");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        try{
            if (amount <= 0){
                throw new IllegalArgumentException("存款金额不能小于等于0！");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        synchronized (bankAccount) {
            bankAccount.deposit(amount);
        }
        System.out.println("存款成功！");
    }
    void withdraw() {
        System.out.print("请输入账户ID：");
        String accountId = scanner.nextLine();
        BankAccount1 bankAccount = bankAccounts.get(accountId);
        if(bankAccount == null){
            System.out.println("账户ID不存在！");
            return;
        }
        System.out.print("请输入取款金额：");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        try{
            if (amount <= 0){
                throw new IllegalArgumentException("取款金额不能小于等于0！");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        synchronized (bankAccount) {
            bankAccount.withdraw(amount);
        }
        System.out.println("取款成功！");
    }
    void queryBalance() {
        System.out.print("请输入账户ID：");
        String accountId = scanner.nextLine();
        if(!bankAccounts.containsKey(accountId)){
            System.out.println("账户ID不存在！");
            return;
        }
        System.out.println("账户余额为：" + bankAccounts.get(accountId).getBalance());
    }
    void saveBankAccounts() {
        if(currentCreatedBankAccount.isEmpty()){
            System.out.println("没有账户信息可保存！");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            for (BankAccount1 bankAccount : currentCreatedBankAccount) {
                writer.write(bankAccount.toFileString());
                writer.newLine();
            }
            System.out.println("账户信息保存成功！"+currentCreatedBankAccount.size()+"个账户");
            for(BankAccount1 bankAccount : currentCreatedBankAccount){
                System.out.println(bankAccount.toString());
            }
        } catch (IOException e) {
            System.out.println("保存账户信息到文件时出错：" + e.getMessage());
        }
    }
    void loadBankAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                BankAccount1 bankAccount = BankAccount1.getBankAccount(line);
                if (bankAccount != null) {
                    bankAccounts.put(bankAccount.getAccountId(), bankAccount);
                }
            }
        } catch (IOException e) {
            System.out.println("从文件加载账户信息时出错：" + e.getMessage());
        }
    }
    static void testThreadSafety() {
        BankAccountSystem bankAccountSystem = new BankAccountSystem();
        BankAccount1 testAccount = new BankAccount1("123456", "张三", 1000);
        bankAccountSystem.bankAccounts.put("123456", testAccount);
        System.out.printf("测试账户初始余额为：%.2f%n", testAccount.getBalance());
        Thread depositThread = new Thread(new Runnable() {
            @Override
            public void run() {
                testAccount.deposit(500);
                testAccount.deposit(500);
                testAccount.deposit(500);
                System.out.printf("线程存款完成,当前余额为：%.2f%n", testAccount.getBalance());
            }
        });
        Thread withdrawThread = new Thread(new Runnable() {
            @Override
            public void run() {
                testAccount.withdraw(100);
                testAccount.withdraw(100);
                testAccount.withdraw(100);
                System.out.printf("线程取款完成,当前余额为：%.2f%n", testAccount.getBalance());
            }
        });
        depositThread.start();
        withdrawThread.start();
        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("测试账户最终余额为：%.2f%n", testAccount.getBalance());
    }
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        BankAccountSystem bankAccountSystem = new BankAccountSystem();
        while (true){
            System.out.println("请选择您要进行的操作：");
            System.out.println("1. 创建账户");
            System.out.println("2. 存款");
            System.out.println("3. 取款");
            System.out.println("4. 查询余额");
            System.out.println("5. 保存账户信息到文件");
            System.out.println("6. 执行线程安全测试");
            System.out.println("7. 退出系统");
            System.out.print("请选择功能（1-7）：");
            int choice = bankAccountSystem.scanner.nextInt();
                bankAccountSystem.scanner.nextLine();
                switch (choice){
                case 1:
                    bankAccountSystem.createBankAccount();
                    break;
                case 2:
                    bankAccountSystem.deposit();
                    break;
                case 3:
                    bankAccountSystem.withdraw();
                    break;
                case 4:
                    bankAccountSystem.queryBalance();
                    break;
                case 5:
                    bankAccountSystem.saveBankAccounts();
                    break;
                case 6:
                    bankAccountSystem.testThreadSafety();
                    break;
                case 7:
                    System.out.println("谢谢使用！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
}

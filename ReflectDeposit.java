import java.lang.reflect.Method;

public class ReflectDeposit {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        try{
            Class<?> bankAccountClass = Class.forName("BankAccount1");
            Object bankAccountInstance = bankAccountClass.getDeclaredConstructor(String.class, String.class,double.class).newInstance("1234567890","张三", 1000.0);
            Method depositMethod = bankAccountClass.getMethod("deposit", double.class);
            double balance = 1000.0;
            depositMethod.invoke(bankAccountInstance, balance);
            System.out.println("存款成功！");
            Method getBalance = bankAccountClass.getMethod("getBalance");
            double currentBalance = (double) getBalance.invoke(bankAccountInstance);
            System.out.println("当前余额为：" + currentBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

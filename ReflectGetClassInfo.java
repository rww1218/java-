import java.util.Scanner;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectGetClassInfo {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入类名：");
        String className = sc.nextLine();
        sc.close();
        try{
            Class<?> clazz = Class.forName(className);
            System.out.println("\n属性：");
            for(Field field : clazz.getDeclaredFields()){
                System.out.println(field);
            }
            System.out.println("\n方法：");
            for(Method method : clazz.getDeclaredMethods()){
                System.out.println(method);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

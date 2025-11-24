import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PersonComparator {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc=new Scanner(System.in);
        ArrayList<person> list=new ArrayList<>();
        while(true){
            System.out.println("请输入姓名和年龄(小于0则输入结束)，以空格分隔：");
            String name=sc.next();
            int age=sc.nextInt();
            if(age<0){
                break;
            }
            person p=new person(name,age);
            list.add(p);
        }
        Collections.sort(list,new Comparator<person>(){
            @Override
            public int compare(person o1, person o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        System.out.println("按年龄排序后的结果为：");
        for(person p:list){
            System.out.println(p);
        }
    }
}

class person{
    private String name;
    private int age;
    public person(String name,int age){
        this.name=name;
        this.age=age;
        }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    @Override
    public String toString(){
        return "姓名："+this.name+"，年龄："+this.age;
    }
}
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleProductSystem {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        while(true){
            System.out.println("请选择您的操作：1.添加商品 2.查询商品 3.退出");
            int choice = sc.nextInt();
            if(choice == 1){
                System.out.println("请输入商品ID");
                int id = sc.nextInt();
                System.out.println("请输入商品名称");
                String name = sc.next();
                System.out.println("请输入商品价格");
                double price = sc.nextDouble();
                Product product = new Product(id, name, price);
                products.add(product);
                System.out.println("添加商品成功");
            }
            else if(choice == 2){
                System.out.println("请输入商品ID");
                int id = sc.nextInt();
                boolean found = false;
                for(Product product : products){
                    if(product.getId() == id){
                        System.out.println(product);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    System.out.println("商品不存在");
                }
            }
            else if(choice == 3){
                System.out.println("谢谢使用");
                break;
            }
            else{
                System.out.println("您的操作有误");
            }
        }

    }
}

class Product{
    private int id;
    private String name;
    private double price;
    public Product(){
    }
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    @Override
    public String toString(){
        return "商品ID：" + this.id + "，商品名称：" + this.name + "，商品价格：" + this.price;
    }
}
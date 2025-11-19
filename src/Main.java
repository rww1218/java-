import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add product");
            System.out.println("2. Show products");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    Manager.addProduct();
                    break;
                case 2:
                    Manager.showProducts();
                    break;
                case 3:
                    System.out.println("退出系统成功！");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误！请选择 1-3 之间的数字。");
            }
        }
    }
}

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Manager {

    private static List<Product> products = new ArrayList<>();

    public static void addProduct() {
        System.out.println("\n===== 添加商品 =====");

        System.out.print("请输入商品ID（非负整数）：");
        final int id;
        while (true) {
            if (Main.sc.hasNextInt()) {
                int tempId = Main.sc.nextInt();
                Main.sc.nextLine();
                if (tempId >= 0) {

                    boolean isDuplicate = products.stream()
                            .anyMatch(p -> p.getId() == tempId);
                    if (isDuplicate) {
                        System.out.print("该ID已存在！请重新输入商品ID：");
                    } else {
                        id = tempId;
                        break;
                    }
                } else {
                    System.out.print("ID不能为负数！请重新输入：");
                }
            } else {
                System.out.print("输入格式错误！请输入非负整数ID：");
                Main.sc.next();
            }
        }

        System.out.print("请输入商品名称：");
        String name = Main.sc.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("商品名称不能为空！请重新输入：");
            name = Main.sc.nextLine().trim();
        }

        System.out.print("请输入商品价格（正数）：");
        double price;
        while (true) {
            if (Main.sc.hasNextDouble()) {
                price = Main.sc.nextDouble();
                Main.sc.nextLine();
                if (price > 0) {
                    break;
                } else {
                    System.out.print("价格必须为正数！请重新输入：");
                }
            } else {
                System.out.print("输入格式错误！请输入有效价格：");
                Main.sc.next();
            }
        }

        products.add(new Product(id, name, price));
        System.out.println("商品添加成功！");
    }

    public static void showProducts() {
        System.out.println("\n===== 查询商品 =====");
        System.out.println("查询条件选择：");
        System.out.println("1. 按商品ID查询");
        System.out.println("2. 按商品名称查询（模糊匹配）");
        System.out.println("3. 按价格区间查询");
        System.out.println("4. 查询所有商品");
        System.out.print("请选择查询方式（1-4）：");

        int queryChoice = Main.sc.nextInt();
        Main.sc.nextLine();

        List<Product> result = new ArrayList<>();

        switch (queryChoice) {
            case 1:
                System.out.print("请输入要查询的商品ID：");
                int queryId = Main.sc.nextInt();
                Main.sc.nextLine();
                for (Product p : products) {
                    if (p.getId() == queryId) {
                        result.add(p);
                        break;
                    }
                }
                break;
            case 2:
                System.out.print("请输入商品名称关键词：");
                String keyword = Main.sc.nextLine().trim().toLowerCase();
                for (Product p : products) {
                    if (p.getName().toLowerCase().contains(keyword)) {
                        result.add(p);
                    }
                }
                break;
            case 3:
                System.out.print("请输入最低价格：");
                double minPrice = Main.sc.nextDouble();
                System.out.print("请输入最高价格：");
                double maxPrice = Main.sc.nextDouble();
                Main.sc.nextLine();

                if (minPrice > maxPrice) {
                    System.out.println("最低价格不能大于最高价格！查询失败。");
                    result.clear();
                    return;
                }

                for (Product p : products) {
                    if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                        result.add(p);
                    }
                }
                break;
            case 4:
                result = products;
                break;
            default:
                System.out.println("输入错误！查询失败。");
                result.clear();
                return;
        }

        System.out.println("\n===== 查询结果 =====");
        if (result.isEmpty()) {
            System.out.println("未找到符合条件的商品！");
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i));
            }
        }
    }
}

public class ShapeSystem {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Circle circle = new Circle(2);
        circle.displayInfo();
        Rectangle rectangle = new Rectangle(5,20);
        rectangle.displayInfo();
        Triangle triangle = new Triangle(3,4,5);
        triangle.displayInfo();
    }
}

interface Shape {
    double calculateArea();    // 计算面积
    double calculatePerimeter();// 计算周长
    String getShapeName();     // 获取图形名称
    void displayInfo();        // 显示图形信息
}

class Circle implements Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double calculateArea() {
        return Math.PI*radius*radius;
        }
    @Override
    public double calculatePerimeter() {
        return 2*Math.PI*radius;
    }
    @Override
    public String getShapeName() {
        return "圆";
    }
    @Override
    public void displayInfo() {
        System.out.println("这是一个圆，半径:"+radius+"，面积:"+calculateArea()+"，周长:"+calculatePerimeter());
    }
}

class Rectangle implements Shape {
    private double length;
    private double width;
    public Rectangle(double length,double width) {
        this.length = length;
        this.width = width;
    }
    @Override
    public double calculateArea() {
        return length*width;
    }
    @Override
    public double calculatePerimeter() {
        return 2*(length+width);
    }
    @Override
    public String getShapeName() {
        return "矩形";
    }
    @Override
    public void displayInfo() {
        System.out.println("这是一个矩形，长:"+length+"，宽:"+width+"，面积:"+calculateArea()+"，周长:"+calculatePerimeter());
    }
}

class Triangle implements Shape {
    private double a;
    private double b;
    private double c;
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double calculateArea() {
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    @Override
    public double calculatePerimeter() {
        return a+b+c;
    }
    @Override
    public String getShapeName() {
        return "三角形";
    }
    @Override
    public void displayInfo() {
        System.out.println("这是一个三角形，边长a:"+a+"，边长b:"+b+"，边长c:"+c+"，面积:"+calculateArea()+"，周长:"+calculatePerimeter());
    }
}
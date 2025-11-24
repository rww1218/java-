import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Company {
    ArrayList<Employee> employees = new ArrayList<>();
    public void addEmployee(Employee employee) {
        if(!employees.contains(employee)){
            employees.add(employee);
            System.out.println("员工"+employee.getName()+"添加成功");
        }else{
            System.out.println("员工"+employee.getId()+"已存在");
        }
    }
     public void removeEmployee(Employee employee) {
        if(employees.contains(employee)){
            employees.remove(employee);
            System.out.println("员工"+employee.getName()+"删除成功");
        }else{
            System.out.println("员工"+employee.getId()+"不存在");
        }
    }
    public double calculateSalary(){
        return employees.stream().mapToDouble(Employee::calculateSalary).sum();
    }
    public void displayEmployees(){
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort(Comparator.comparingDouble(Employee::calculateSalary).reversed());
        sortedEmployees.forEach(System.out::println);
    }


    public void findMaxSalaryEmployee() {
        Optional<Employee> maxSalaryEmp = employees.stream()
                .max(Comparator.comparingDouble(Employee::calculateSalary));

        if (maxSalaryEmp.isPresent()) {
            Employee emp = maxSalaryEmp.get();
            System.out.println("\n工资最高员工：" + emp);
        } else {
            System.out.println("\n公司暂无员工！");
        }
    }

    public void findMinSalaryEmployee() {
        Optional<Employee> minSalaryEmp = employees.stream()
                .min(Comparator.comparingDouble(Employee::calculateSalary));

        if (minSalaryEmp.isPresent()) {
            Employee emp = minSalaryEmp.get();
            System.out.println("工资最低员工：" + emp);
        } else {
            System.out.println("公司暂无员工！");
        }
    }


    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Company company = new Company();
        company.addEmployee(new FullEmployee("001", "张三", 5000, 1000));
        company.addEmployee(new PartTimeEmployee("002", "李四", 200, 100, 40));
        company.addEmployee(new FullEmployee("003", "王五", 7000, 2000));
        company.displayEmployees();
        company.findMaxSalaryEmployee();
        company.findMinSalaryEmployee();
        Employee employee = new PartTimeEmployee("002", "李四", 200, 100,40);
        company.removeEmployee(employee);
        company.displayEmployees();
    }
}

abstract class Employee {
    private String id;
    private String name;
    private double baseSalary;
    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double calculateSalary();
    public String toString(){
        return "Employee{" +
                "id='" + id  +
                ", name='" + name  +
                ", baseSalary=" + baseSalary +
                ", salary=" + calculateSalary() +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id.equals(employee.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

class FullEmployee extends Employee {
    private double Bonus;
    public FullEmployee(String id, String name, double baseSalary, double Bonus) {
        super(id, name, baseSalary);
        this.Bonus = Bonus;
    }
    @Override
    public double calculateSalary() {
        return getBaseSalary() + Bonus;
    }
}

class PartTimeEmployee extends Employee {
    private double hourWage;
    private int hoursWorked;
    public PartTimeEmployee(String id, String name, double baseSalary, double hourWage, int hoursWorked) {
        super(id, name, baseSalary);
        this.hourWage = hourWage;
        this.hoursWorked = hoursWorked;
    }
    @Override
    public double calculateSalary() {
        return hourWage * hoursWorked;
    }
}

class SalesEmployee extends Employee {
    private  double salesAmount;
    public SalesEmployee(String id, String name, double salesAmount) {
        super(id, name, 0);
        this.salesAmount = salesAmount;
    }
    @Override
    public double calculateSalary() {
        return getBaseSalary() + salesAmount * 0.05;
    }
}
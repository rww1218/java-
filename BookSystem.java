import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BookSystem {
    private static  final String FilePath = "books.txt";
    private ArrayList<Book> books = new ArrayList<Book>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        BookSystem bookSystem = new BookSystem();
        while (true){
            System.out.println("1. 添加图书");
            System.out.println("2. 查看所有图书（按ISBN升序）");
            System.out.println("3. 借书");
            System.out.println("4. 还书");
            System.out.println("5. 保存图书信息到文件");
            System.out.println("6. 退出系统");
            System.out.print("请选择功能（1-6）：");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    bookSystem.addBook();
                    break;
                case 2:
                    bookSystem.viewBooks();
                    break;
                case 3:
                    bookSystem.borrowBook();
                    break;
                case 4:
                    bookSystem.returnBook();
                    break;
                case 5:
                    bookSystem.saveBooks();
                    break;
                case 6:
                    System.out.println("谢谢使用！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }
    private void addBook(){
        System.out.print("请输入ISBN：");
        String isbn = sc.nextLine();
        for(int i=0;i<books.size();i++){
            if(books.get(i).getIsbn().equals(isbn)){
                System.out.println("ISBN已存在，添加失败！");
                return;
            }
        }
        System.out.print("请输入书名：");
        String bookName = sc.nextLine();
        System.out.print("请输入作者：");
        String author = sc.nextLine();
        System.out.print("请输入库存：");
        int stock = sc.nextInt();
        sc.nextLine();
        books.add(new Book(isbn, bookName, author, stock));
        System.out.println("图书添加成功！");
    }
    private void viewBooks(){
        if(books.isEmpty()){
            System.out.println("当前无图书信息！");
            return;
        }
        Collections.sort(books, Comparator.comparing(Book::getIsbn));
        for(Book book:books){
            System.out.println(book);
        }
    }
    private void borrowBook(){
        System.out.print("请输入要借的图书ISBN：");
        String isbn = sc.nextLine();
        for(int i=0;i<books.size();i++){
            if(books.get(i).getIsbn().equals(isbn)){
                if(books.get(i).getStock()>0){
                    books.get(i).setStock(books.get(i).getStock()-1);
                    System.out.println("借阅成功！");
                    return;
                }
                else{
                    System.out.println("库存不足，借阅失败！");
                    return;
                }
            }
        }
        System.out.println("未找到该图书，借阅失败！");
    }
    private void returnBook(){
        System.out.print("请输入要还的图书ISBN：");
        String isbn = sc.nextLine();
        for(int i=0;i<books.size();i++){
            if(books.get(i).getIsbn().equals(isbn)){
                if(books.get(i).getStock()>=books.get(i).getInitialStock()){
                    System.out.println("该图书未被借出，无需还书！");
                    return;
                }
                if(books.get(i).getStock()<0){
                    System.out.println("该图书已被还过，无需重复还！");
                    return;
                }
                books.get(i).setStock(books.get(i).getStock()+1);
                System.out.println("还书成功！");
                return;
            }
        }
        System.out.println("未找到该图书，还书失败！");
    }
    private void saveBooks(){
        try{
            FileWriter fw = new FileWriter(FilePath);
            for(Book book:books){
                fw.write(book.toFileString() + "\n");
            }
            fw.close();
            System.out.println("图书信息保存成功！");
        }
        catch(IOException e){
            System.out.println("图书信息保存失败！");
        }
    }
}

class Book{
    private String isbn;
    private String bookName;
    private String author;
    private int stock;
    private int initialStock;
    public Book(String isbn, String bookName, String author, int stock) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.stock = stock;
        this.initialStock = stock;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getBookName() {
        return bookName;
    }
    public String getAuthor() {
        return author;
    }
    public int getStock() {
        return stock;
    }
    public int getInitialStock() {
        return initialStock;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn +
                ", bookName='" + bookName  +
                ", author='" + author  +
                ", stock=" + stock +
                '}';
    }
    public String toFileString(){
        return isbn + "," + bookName + "," + author + "," + stock;
    }
    public static Book fromFileString(String line){
        String[] parts = line.split(",");
        return new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
    }
}
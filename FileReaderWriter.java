import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderWriter {
    public static void main(String[] args) throws IOException {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要存到文件中的内容");
        String content = sc.nextLine();
        sc.close();
        FileWriter fw = new FileWriter("test.txt");
        fw.write(content);
        fw.close();
        System.out.println("内容已成功写入文件");
        FileReader fr = new FileReader("test.txt");
        StringBuffer sb = new StringBuffer();
        int num = fr.read();
        while (num != -1) {
            sb.append((char)num);
            num = fr.read();
        }
        fr.close();
        System.out.println("文件内容为：" + sb.toString());
    }
}

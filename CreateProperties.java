import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateProperties {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        String filePath = "config.properties";
        File file = new File(filePath);
        try{
            if(file.createNewFile()){
                System.out.println("文件创建成功！");
                Properties prop = new Properties();
                prop.setProperty("username", "张三");
                prop.setProperty("password", "123456");
                prop.setProperty("balance", "1000.0");
                prop.store(new FileOutputStream(file), "默认属性");
            } else {
                System.out.println("文件已存在！");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(file)){
            properties.load(fis);
            System.out.println("属性加载成功！");
            for(Object key : properties.keySet()){
                System.out.println(key + " = " + properties.get(key));
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

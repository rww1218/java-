import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        System.out.println("计科六班-任雯-2405010624");
        FileReader fr=new FileReader("test.txt");
        int count=0;
        boolean isWord=false;
        int i;
        StringBuffer sb=new StringBuffer();
        while((i=fr.read())!=-1){
            char ch=(char)i;
            sb.append(ch);
            if(i==' '||i=='\n'||i=='\t'){
                isWord=false;
            }
            else if(!isWord){
                count++;
                isWord=true;
            }
        }
        fr.close();
        System.out.println("文件中的内容为："+sb.toString());
        System.out.println("文件中的单词数为："+count);
    }
}

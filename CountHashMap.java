import java.util.HashMap;
import java.util.Scanner;

public class CountHashMap {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        HashMap<Character,Integer> map=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)+1);
            }
            else{
                map.put(ch,1);
            }
        }
        System.out.print("字符出现的次数为："+map);
    }
}

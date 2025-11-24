import java.util.ArrayList;
import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> list=new ArrayList<>();
        while(sc.hasNextInt()){
            list.add(sc.nextInt());
        }
        for(int i=0;i<list.size()-1;i++){
            for(int j=0;j<list.size()-1-i;j++){
                if(list.get(j)>list.get(j+1)){
                    int temp=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
            }
        }
        System.out.println("排序后的结果为："+list);
    }
}

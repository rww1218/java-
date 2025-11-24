public class TwoThread {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Thread t1=new Thread(){
            @Override
            public void run() {
                for(int i=1;i<=10;i++){
                    System.out.print(i+" ");
                }
            }
        };
        Thread t2=new Thread(){
            @Override
            public void run() {
                for(char i='A';i<='J';i++){
                    System.out.print(i+" ");
                }
            }
        };
        t1.start();
        t2.start();

    }
}

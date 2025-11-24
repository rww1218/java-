import java.util.LinkedList;
import java.util.Queue;

public class SimpleProducerConsumer {
    public static void main(String[] args) {
        System.out.println("计科六班-任雯-2405010624");
        Buffer buffer=new Buffer();
        new Thread(new Producer(buffer)).start();
        new Thread(new Consumer(buffer)).start();
    }
}

class Buffer{
    private final Queue<Integer> queue=new LinkedList<>();
    private static final int CAPACITY=10;
    public synchronized void produce(int item){
        while(queue.size()==CAPACITY){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(item);
        System.out.println("生产："+item);
        notifyAll();
    }
    public synchronized int consume(){
        while(queue.isEmpty()){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int item=queue.poll();
        System.out.println("消费："+item);
        notifyAll();
        return item;
    }
}
class Producer implements Runnable{
    private Buffer buffer;
    public Producer(Buffer buffer){
        this.buffer=buffer;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            buffer.produce(i);
        }
    }
}
class Consumer implements Runnable{
    private Buffer buffer;
    public Consumer(Buffer buffer){
        this.buffer=buffer;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            buffer.consume();
        }
    }
}

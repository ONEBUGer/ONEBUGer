package test;

/**
 * @ClassName DeadlockDemo
 * @Description TODO
 * @Author 铿然一叶
 * @Date 2019/10/3 23:40
 * javashizhan.com
 **/
public class DeadlockDemo {
    public static void main(String[] args) {
        //创建两个用于加锁的对象
        final Object lockX = new Object();
        final Object lockY = new Object();

        System.out.println("lockX " + lockX);
        System.out.println("lockY " + lockY);

        Thread tX = new Thread(new Worker(lockX, lockY), "tX");
        //交换锁的顺序，模拟死锁
        Thread tY = new Thread(new Worker(lockY, lockX), "tY");

        tX.start();
        tY.start();
    }
}

class Worker implements Runnable {

    private final Object lockX;

    private final Object lockY;

    public Worker(Object lockX, Object lockY) {
        this.lockX = lockX;
        this.lockY = lockY;
    }

    public void run() {
        synchronized (lockX) {
            //休眠一会，等待另外一个线程获取到lockY
            sleep(2000);
            System.out.println(Thread.currentThread().getName() + " get lock " + lockX);

            synchronized (lockY) {
                //这一步由于发生了死锁永远不会执行
                System.out.println(Thread.currentThread().getName() + " get lock " + lockY);
            }
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



class Producer extends Thread {
    private Buffer blank;

    //constructor goes here
    public Producer(Buffer blank) {
        this.blank = blank;
    }

    public void run() {
        for (int i=0; i<10; i++) {
            blank.put(i);

            System.out.println("Producer: Produced" + i);
            try {
                sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {

            }
        }
    }
}

class Consumer extends Thread {
    private Buffer blank;

    //constructor goes here
    public Consumer(Buffer blank) {
        this.blank = blank;
    }

    public void run() {
        int value = 0;

        for (int i=0; i<10; i++) {
            value = blank.get();
            System.out.println("Consumer: Consumed" + value);
        }
    }
}

class Buffer {
    private int contents;
    private boolean available = false;

    public synchronized int get() {
        //if there is no available contents, keep waiting
        while (!available) {
            try {
//                System.out.println("not available -> impossible to get -> wait");
                wait();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        available = false;
        notify();
        return contents;

        //if there is available contents, print the contents on screen (this is like consuming new contents), and notify
        //set available to false
        //return contents
    }

    public synchronized void put(int value) {
        while (available) {
            try {
//                System.out.println("available -> impossible to put -> wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        contents = value;
        available = true;
        notify();

        //if there is available contents, keep waiting
        //if there is no available contents, store value to contents (this is like producing new contents), and notify
        //set available to true
    }
}


public class ProducerConsumerProblem {
    public static void main(String[] args) {
        Buffer b = new Buffer();
        Producer p1 = new Producer(b);
        Consumer c1 = new Consumer(b);

        p1.start();
        c1.start();

    }
}


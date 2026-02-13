package multithreading.sequential;

public class SequentialExecution {
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    static void demo1() {
        for (int i = 0; i < 5; i++) {
            System.out.println("From demo1 " + i);
        }
    }

    static void demo2() {
        for (int i = 0; i < 5; i++) {
            System.out.println("From demo2 " + i);
        }
    }
}

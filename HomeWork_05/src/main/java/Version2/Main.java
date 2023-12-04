package Version2;

import java.util.concurrent.CountDownLatch;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Философ может взять только две вилки сразу, то есть обе вилки должны быть свободны
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class Main {
    private static final int PHILOSOPH = 5;
    public static void main(String[] args) {
        CountDownLatch cdlAllEats = new CountDownLatch(PHILOSOPH);
        Table table = new Table(cdlAllEats);
        Thread thread = new Thread(new Philosoph("Aristotel", table, cdlAllEats));
        Thread thread1 = new Thread(new Philosoph("Socrat", table, cdlAllEats));
        Thread thread2 = new Thread(new Philosoph("Makedonsky", table, cdlAllEats));
        Thread thread3 = new Thread(new Philosoph("Platon", table, cdlAllEats));
        Thread thread4 = new Thread(new Philosoph("Pifagor", table, cdlAllEats));
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

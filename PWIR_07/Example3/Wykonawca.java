package Example3;

import java.util.concurrent.*;

public class Wykonawca {
    public static void main(String[] args) throws InterruptedException{
      //definicja 4 wątków
      Task t1 = new Task("1");
      Task t2 = new Task("2");
      Task t3 = new Task("3");
      Task t4 = new Task("4");

      Thread th1 = new Thread(t1);
      Thread th2 = new Thread(t2);
      Thread th3 = new Thread(t3);
      Thread th4 = new Thread(t4);

      //uruchamiamy ale nie kontrolujemy ich
      //nie panujemy nad kolejnością
      th1.start();
      th2.start();
      th3.start();
      th4.start();

      Thread.sleep(3000);
      System.out.println("Drugie przejście!");

      th1 = new Thread(t1);
      th2 = new Thread(t2);
      th3 = new Thread(t3);
      th4 = new Thread(t4);

      //definiwowanie kolejności
      th1.start();
      th1.join(1000); //czeka 1000 ms lub do momentu aż th1 umrze
      th2.start();
      th2.join();     //czeka aż th2 umrze
      th3.start();    //3 i 4 uruchamiają się jak chcą
      th4.start();

      Thread.sleep(3000);
      System.out.println("Koniec maina!");
    }
  }
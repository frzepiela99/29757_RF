package Example2;

import java.util.concurrent.*;

public class Wykonawca {
    public static void main(String[] args) {
      ExecutorService exec = Executors.newFixedThreadPool(2);
      for (int i=1; i<=4; i++) {
        exec.execute(new Task(String.valueOf(i)));
      }

      //kończenie 
      Thread.yield();
      exec.shutdown();

      //czekamy na zakończenie wszystkich zadań
      try {
        exec.awaitTermination(5, TimeUnit.SECONDS);
      } catch(InterruptedException exc) {  }
      System.out.println("Wykonawaca zabity: " + exec.isTerminated());

      //teraz wyświetli się na końcu
      System.out.println("Koniec maina!");
    }
  }
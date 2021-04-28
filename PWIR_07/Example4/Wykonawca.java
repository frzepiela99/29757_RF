package Example4;

import java.util.concurrent.*;

public class Wykonawca {
    public static void main(String[] args){
      ExecutorService exec = Executors.newFixedThreadPool(2);
      for (int i=1; i<=4; i++) {
        exec.execute(new Task(String.valueOf(i)));
      }

      //kończenie 
      try {
        Thread.sleep(1);
      } catch(InterruptedException exc) {}
      exec.shutdownNow();

      //teraz wyświetli się na końcu
      System.out.println("Koniec maina!");
    }
  }
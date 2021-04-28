package Example1;

import java.util.concurrent.*;

public class Wykonawca {
    public static void main(String[] args) {
      //definicja wykonawcy z ograniczeniem do 2 wątków
      ExecutorService exec = Executors.newFixedThreadPool(2);
      for (int i=1; i<=4; i++) {
        exec.execute(new Task(String.valueOf(i)));
      }

      //kończenie/zabijanie wykonawcy
      Thread.yield();
      exec.shutdown();

      //czemu to się wyświetla na początku?
      System.out.println("Koniec maina!");
    }
  }
package Example5;

import java.util.concurrent.*;

public class Wykonawca {
    public static void main(String[] args){
      ExecutorService exec = Executors.newFixedThreadPool(2);

      TaskW t1 = new TaskW();
      TaskL t2 = new TaskL();
      t1.Calculate(5000);
      t2.Calculate(5000);

      FutureTask<Double> task1 = new FutureTask<Double>(t1);
      FutureTask<Double> task2 = new FutureTask<Double>(t2);

      exec.execute(task1);
      exec.execute(task2);

      //kończenie/zabijanie wykonawcy
      Thread.yield();
      exec.shutdown();

      try {
        exec.awaitTermination(10, TimeUnit.SECONDS);
      } catch(InterruptedException exc) {}
      System.out.println("Wykonawaca zabity: " + exec.isTerminated());
      
      try{
        Double v1 = task1.get();
        Double v2 = task1.get();

        if(v1 == v2){
          System.out.println("Ten sam wynik!");
          System.out.println(v1 + " " + v2);
        }
        else{
          System.out.println("Różny wynik!");
          System.out.println(v1 + " " + v2);
        }
      }
      catch(Exception ex) {}

      //teraz wyświetli się na końcu
      System.out.println("Koniec maina!");
    }
  }
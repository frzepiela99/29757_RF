package Example6;

import java.util.concurrent.*;
import java.util.Scanner;

import Example5.TaskL;
import Example5.TaskW;

public class Wykonawca {
    public static void main(String[] args){
      ExecutorService exec = Executors.newFixedThreadPool(2);

      TaskW t1 = new TaskW();
      TaskL t2 = new TaskL();
      t1.Calculate(500000000);
      t2.Calculate(500000000);

      Double v1 = t1.call();
      Double v2 = t2.call();

      System.out.println("Podaj promień okręgu");
      Scanner in = new Scanner(System.in);
      int r = in.nextInt();
      
      System.out.println((v1*r*r) + " - metoda Leibniza");
      System.out.println((v2*r*r) + " - metoda Wallisa");
    }
  }
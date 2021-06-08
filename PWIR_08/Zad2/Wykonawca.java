import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

public class Wykonawca {
    public static void main(String[] args) throws InterruptedException{
        
        int availThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Dostępne wątki = " + availThreads);

        Vector<Vector<Long>>  matrix= new Vector<Vector<Long>>();

        for(int i=0;i<availThreads;i++){
            Vector<Long> r=new Vector<>();
            for(int j=0;j<availThreads;j++){
                r.add(Math.round(Math.random()*10));
            }
            matrix.add(r);
        }

        System.out.println("\nMacierz: ");
        for(int i=0;i<availThreads;i++){
            Vector<Long> r=matrix.get(i);
            for(int j=0;j<availThreads;j++){
                System.out.print(r.get(j));
                if(j<availThreads-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        
        Vector<Long> v = new Vector<Long>();
        for(int i=0;i<availThreads;i++){
            v.add(Math.round(Math.random()*10));
        }

        System.out.println("\nWektor: ");
        for(int i=0;i<availThreads;i++){
            System.out.print(v.get(i));
            if(i<availThreads-1){
                System.out.print(" ");
            }
        }
        System.out.println("");

        class MnoMac implements Runnable {
            private int row;
            public void setRow(int row){
                this.row = row;
            } 
            @Override
            public void run(){
                Vector<Long> r = matrix.get(row);
                for(int i=0;i<availThreads;i++){
                    long temp = r.get(i) * v.get(i);
                    r.set(i, temp);
                    matrix.set(row, r);
                }
            }
        }

        ExecutorService exec = Executors.newFixedThreadPool(availThreads);

        for (int i = 0; i<availThreads; i++)
        {
            MnoMac t= new MnoMac();
            t.setRow(i);
            exec.execute(t);
        }

        Thread.yield();
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("\nWynik: ");
        for(int i=0;i<availThreads;i++){
            Vector<Long> r=matrix.get(i);
            for(int j=0;j<availThreads;j++){
                System.out.print(r.get(j));
                if(j<availThreads-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
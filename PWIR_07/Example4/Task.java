package Example4;

class Task implements Runnable {

  private String name;

  public Task(String name) {
    this.name = name;
  }

  public void run() {
    for (int i=1; i <= 4; i++) {
      if (Thread.currentThread().isInterrupted()) return;
      System.out.println("Task id:" + name + " iteration:" + i);

      Thread.yield();
    }
  }
}
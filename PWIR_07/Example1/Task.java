package Example1;

//definiujemy klase o nazwie Task
//klasa ta implementuje interfejs Runnable
class Task implements Runnable {
  //zwykłe pole klasy typu tekstowego
  private String name;

  //konstruktor
  public Task(String name) {
    this.name = name;
  }

  //metoda wymuszona przez interfejs Runnable
  public void run() {
    //nasze zadanie liczy od 1 do 4 i wyświetla daną liczbę
    //oprócz tego wyświetla także swoje Id
    for (int i=1; i <= 4; i++) {
      System.out.println("Task id:" + name + " iteration:" + i);

      //yield mówi, że wątek nic nie robi
      //pozwala to systemowi uruchomić kolejne jeżeli takie istnieją
      Thread.yield();

      //Thread.sleep(1000); //usypia na 1000 ms
    }
  }
}
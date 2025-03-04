/**
 * @author Adam Long
 */

public class Main
{
   public static void main(String[] args)
   {
      System.out.println("--------------PROBLEM 1---------------");
      System.out.println("--------------------------------------");
      System.out.println();

      Process[] processList = new Process[1000];
      int currTime = 0;
      double requestedTime;
      double totalRequestedTime = 0;

      for (int i=0; i<1000; i++)
      {
         currTime += DistributionCalculations.poissonVariable();

         requestedTime = DistributionCalculations.exponentialVariable(1.0);
         totalRequestedTime += requestedTime;

         processList[i] = new Process(i+1, currTime, requestedTime);
      }

      for (Process p : processList)
      {
         System.out.println(p);
      }

      System.out.println("Avg Arrival Time: " + currTime/1000.0);
      System.out.println("Avg Service TIme: " + totalRequestedTime/1000.0);

      System.out.println();
      System.out.println("--------------PROBLEM 2---------------");
      System.out.println("----------------PART A----------------");
      System.out.println();

      DetectDownTime.failureData();

      System.out.println();
      System.out.println("--------------PROBLEM 2---------------");
      System.out.println("----------------PART B----------------");
      System.out.println();

      System.out.println("Whole computing system failed? " + DetectDownTime.detectCompleteFailure());

   }
}

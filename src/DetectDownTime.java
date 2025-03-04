import java.util.ArrayList;

/**
 * @author Adam Long
 */
public class DetectDownTime
{

   private static ArrayList<Integer> serverOneDowntime = new ArrayList<>();
   private static ArrayList<Integer> serverTwoDowntime = new ArrayList<>();
   protected static final int TOTALTIME = 175200;//where the loop accounts for each hour.

   /**
    * Shows failure and restoration data from the two mirrored servers.
    */
   public static void failureData()
   {
      //We know MTBF is 500hrs -- follows an exponential distribution
      //~350 times each server goes down
      //Expected uptime is 171,700hrs
      //Expected downtime is 3,500hrs.
      //We need to keep track of both servers.

      int currTimeServer1 = 0;
      int currTimeServer2 = 0;
      double expCalcServer1;
      double expCalcServer2;


      do
      {
         //Calculate failure values (floored to the last hour)
         expCalcServer1 = Math.floor(DistributionCalculations.exponentialVariable(1.0/500));

         currTimeServer1 += expCalcServer1 + 10;

         serverOneDowntime.add(currTimeServer1 - 10);

      } while (currTimeServer1 < TOTALTIME);
      do
      {
         //Calculate failure values (floored to the last hour)
         expCalcServer2 = Math.floor(DistributionCalculations.exponentialVariable(1.0/500));

         currTimeServer2 += expCalcServer2 + 10;

         serverTwoDowntime.add(currTimeServer2 - 10);

      } while (currTimeServer2 < TOTALTIME);

      serverOneDowntime.forEach( (n) -> System.out.println("Server 1 Failure at: " + n + " | Down until: " + (n+9)));
      serverTwoDowntime.forEach( (n) -> System.out.println("Server 2 Failure at: " + n + " | Down until: " + (n+9)));

      System.out.println();
      System.out.println("Server 1 Downtime Avg: " + serverOneDowntime.size()*10);
      System.out.println("Server 1 Failure Count: " + serverOneDowntime.size());
      System.out.println("Server 2 Downtime Avg: " + serverTwoDowntime.size()*10);
      System.out.println("Server 2 Failure Count: " + serverTwoDowntime.size());

   }

   /**
    * Calculates if both servers go down at the same time.
    * @return state of servers
    */
   public static boolean detectCompleteFailure()
   {
      for (int serverNum1 : serverOneDowntime)
      {
         for (int serverNum2 : serverTwoDowntime)
         {
            int lowRange = serverNum1;
            int highRange = serverNum1 + 9;
            if (serverNum2 >= lowRange && serverNum2 <= highRange)
            {
               System.out.println("Complete failure at hour: " + serverNum1);
               return true;
            }
         }
      }
      return false;
   }
}


/*
0) Set current time for server 1 & 2
1) calculate failure time for server 1 -- note: lambda is 1/500 as that is the expected failure rate.
2) calculate failure time for server 2
3) append failures to respective lists unless currenttime > totaltime
4) set current times to failure time + 10 (restoration)
5) loop 0-4 while server 1 AND 2 currenttime < totaltime
 */
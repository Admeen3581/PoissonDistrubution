import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistributionCalculationsTest
{
   @Test
   void positiveFactorial()
   {
      assertEquals(24, DistributionCalculations.factorial(4));
   }

   @Test
   void poissonFunctional()
   {
      assertEquals(0.09022, DistributionCalculations.poissonEquation(2, 4));
   }
}
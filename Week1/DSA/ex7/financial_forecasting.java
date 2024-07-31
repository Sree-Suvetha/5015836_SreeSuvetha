import java.util.*;

public class financial_forecasting
{
    public static double calculateFutureValue(double principal, double rate, int periods) 
    {
        if (periods == 0) 
        {
            return principal;
        }
        return calculateFutureValue(principal*(1 + rate), rate, periods - 1) ;
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter principal value:");
        double principal = sc.nextDouble();
        System.out.println("Enter rate in percentage:");
        double rate = sc.nextDouble();  
        System.out.println("Enter period:");    
        int periods =sc.nextInt();          

        double futureValue = calculateFutureValue(principal, rate/100, periods);
        System.out.println("Future Value: " + futureValue);
        sc.close();

    }

}
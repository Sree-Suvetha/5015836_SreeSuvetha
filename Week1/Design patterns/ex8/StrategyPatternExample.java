interface PaymentStrategy
{
    void pay();
}

class CreditCardPayment implements PaymentStrategy
{
    public void pay()
    {
        System.out.println("Paid using credit card");
    }
}

class PayPalPayment implements PaymentStrategy
{
    public void pay()
    {
        System.out.println("Paid using Pay Pal");
    }
}

class PaymentContext
{
    PaymentStrategy payment;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.payment = paymentStrategy;
    }

    public void checkout() {
        payment.pay();
    }

}

public class StrategyPatternExample {
    public static void main(String args[])
    {
        PaymentContext cart = new PaymentContext();
        PaymentStrategy creditCardPayment = new CreditCardPayment();
        cart.setPaymentStrategy(creditCardPayment);
        cart.checkout();
        PaymentStrategy payPalPayment = new PayPalPayment();
        cart.setPaymentStrategy(payPalPayment);
        cart.checkout();
    }
}
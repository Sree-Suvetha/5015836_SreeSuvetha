interface PaymentProcessor
{
    void processPayment(double amount);

}

class Amazonpay 
{
    public void apay(String amount)
    {
        System.out.println("Paying through amazon pay Rs- "+amount);
    }
}


class amazonAdapter implements PaymentProcessor
{
    private Amazonpay ap;
    public amazonAdapter(Amazonpay ap)
    {
        this.ap=ap;
    }

    public void processPayment(double amount)
    {
        ap.apay(String.valueOf(amount));
    }

}

class Paypal 
{
    public void ppay(String amount)
    {
        System.out.println("Paying through paypal pay Rs- "+amount);
    }
}

class paypalAdapter implements PaymentProcessor
{
    private Paypal pp;
    public paypalAdapter(Paypal pp)
    {
        this.pp=pp;
    }

    public void processPayment(double amount)
    {
        pp.ppay(String.valueOf(amount));
    }

}

class AdapterpatternExample
{
    public static void main(String[] args) {
        Amazonpay a=new Amazonpay();
        PaymentProcessor adap1=new amazonAdapter(a);
        adap1.processPayment(1000);

        Paypal p=new Paypal();
        PaymentProcessor adap2=new paypalAdapter(p);
        adap2.processPayment(5000);
    }

}





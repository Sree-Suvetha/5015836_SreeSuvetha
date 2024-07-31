interface Notifier
{
    public void send(String message);
}

class EmailNotifier implements Notifier
{
    public void send(String message)
    {
        System.out.println("Notification: " + message);
    }
}

abstract class NotifierDecorator implements Notifier
{
    protected Notifier notify;
    public NotifierDecorator(Notifier notify)
    {
        this.notify=notify;
    }
    public void send(String message)
    {
        notify.send(message);
    }
}

class SMSNotifierDecorator extends NotifierDecorator
{
    SMSNotifierDecorator(Notifier notify)
    {
        super(notify);
    }
    
    public void send(String message)
    {
        super.send(message);
        System.out.println("via SMS ");
    }

}

class SlackNotifierDecorator extends NotifierDecorator
{
    SlackNotifierDecorator(Notifier notify)
    {
        super(notify);
    }
    public void send(String message)
    {
        super.send(message);
        System.out.println("via Slack");
    }
}

public class DecoratorPatternExample
{
    public static void main(String[] args) 
    {
        Notifier email=new EmailNotifier();
        NotifierDecorator sms=new SMSNotifierDecorator(email); 
        NotifierDecorator slack=new SlackNotifierDecorator(sms);

        slack.send("Hello");
        
    }
}



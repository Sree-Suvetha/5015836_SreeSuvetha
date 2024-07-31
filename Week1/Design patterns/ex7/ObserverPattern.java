import java.util.*;
interface Stock
{
    public void register(Observer observer);
    public void deregister(Observer observer);
    public void notifyobserver();

}

class StockMarket implements Stock
{
    
    private List<Observer> observers = new ArrayList<>();
    
    public void register(Observer observer)
    {
        observers.add(observer);
        System.out.println("registered");
    }

    public void deregister(Observer observer)
    {
        observers.remove(observer);
        System.out.println("deregistered");
    }

    public void notifyobserver() {
        System.out.println("Notifying all registered observers...");
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void change() {
        System.out.println("StockMarket state changed.");
        notifyobserver();
    }
}

interface Observer
{
    public void update();
}

class MobileApp implements Observer
{
    private String name;
    public MobileApp(String name)
    {
        this.name=name;
    }
    public void update()
    {
        System.out.println(name + " received update.");
    }
}

class WebApp implements Observer
{
    private String name;
    public WebApp(String name)
    {
        this.name=name;
    }
    public void update()
    {
        System.out.println(name + " received update.");
    }
}

public class ObserverPattern
{
    public static void main(String[] args) {
        StockMarket sm=new StockMarket();
        Observer mobile=new MobileApp("Mobile application");
        Observer web=new WebApp("Web application");

        sm.register(mobile);
        sm.register(web);

        sm.change();
        sm.deregister(web);
        sm.change();
    }
}

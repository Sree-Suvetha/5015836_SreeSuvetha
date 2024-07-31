class Logger
{
    private static Logger instance;
    private String s="hi";

    public String getS() 
    {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    private Logger(String s) {
        this.s = s;
    }

    public static Logger getInstance(String s)
    {
        if(instance==null)
        {
            synchronized(Logger.class)
            {
            if(instance==null)
            {
                instance=new Logger(s);
            }
            }   
        }
        return instance;
    }
}

public class SingletonPatternExample
{
    public static void main(String[] args) {
        Logger log1=Logger.getInstance("start");
        System.out.println("1st message : "+log1.getS());
        Logger log2=Logger.getInstance("change");
        System.out.println("2nd message : "+log2.getS());

        log1.setS("Updated");
        System.out.println("1st message : "+log1.getS());
        System.out.println("2nd message : "+log2.getS());


    }
}


interface Command
{
    public void execute();
}

class LightOnCommand implements Command
{
    private Light light;

    public LightOnCommand(Light light) 
    {
        this.light = light;
    }

    public void execute()
    {
        light.turnOn();
    }
}

class LightOffCommand implements Command
{
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute()
    {
        light.turnOff();
    }
}

class RemoteControl
{
    private Command cmd;
    public void executecommand(Command cmd)
    {
        this.cmd=cmd;
    }
    public void pressButton() {
        cmd.execute();
    }
}

class Light
{
    public void turnOn()
    {
        System.out.println("Light - On");
    }
    public void turnOff()
    {
        System.out.println("Light - Off");
    }
}

public class CommandPattern
{
    public static void main(String[] args) {
        Light light=new Light();
        Command on=new LightOnCommand(light);
        Command off=new LightOffCommand(light);

        RemoteControl remote=new RemoteControl();
        remote.executecommand(on);
        remote.pressButton();

        remote.executecommand(off);
        remote.pressButton();
    }
}
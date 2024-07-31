class Computer
{
    private String cpu;
    private int storage;
    private int ram;
    public String getCpu() 
    {
        return cpu;

    }
    public int getStorage() 
    {
        return storage;
    }
    public int getRam() 
    {
        return ram;
    }
    Computer(Builder builder)
    {
        this.cpu=builder.cpu;
        this.storage=builder.storage;
        this.ram=builder.ram;
    }

    public static class Builder
    {

        private String cpu;
        private int storage;
        private int ram;

        public Builder cpu(String cpu)
        {
            this.cpu=cpu;
            return this;
        }
        public Builder storage(int storage)
        {
            this.storage=storage;
            return this;
        }
        public Builder ram(int ram)
        {
            this.ram=ram;
            return this;
        }
        public Computer build()
        {
            return new Computer(this);
        }
    }
}

public class BuilderPatternExample 
{
    public static void main(String args[])
    {
        Computer comp=new Computer.Builder()
                    .cpu("intel")
                    .storage(500)  //.ram(56)
                    .build();
        System.out.println(comp.getCpu() + " " + comp.getStorage() + " " + comp.getRam());
    }
}
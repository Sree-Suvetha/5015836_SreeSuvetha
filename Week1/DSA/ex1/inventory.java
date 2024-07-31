import java.util.*;

class inventory
{
public static void main(String args[])
{
    InvetoryManagement inv=new InvetoryManagement();
    inv.menu();
}
}
class Product
{
    int productId;
    String productName;
    int quantity;
    double price;
    
    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    
}

class InvetoryManagement
{
    HashMap<Integer,Product> inv = new HashMap<>();
    Scanner sc=new Scanner(System.in);
    
    public void menu()
    {
        System.out.println("\t\t\tInventory management\n");
        int ch;
        do{
            System.out.println("\n1.Add\n2.Update\n3.Delete\n4.Display\n5.Exit\nEnter your choice: ");
            ch=sc.nextInt();
            switch (ch) {
            case 1:
            {
                addp();
                break;
            }
            case 2:
            {
                updatep();
                break;
            }
            case 3:
            {
                deletep();
                break;
            }
            case 4:
            {
                display();
                break;
            }
            case 5:
            {
                return;
            }
            default:
            {
                System.out.println("Enter a valid choice! ");
                break;
            }
        }
                
        }while(ch!=5);
    }

    public void addp()
    {
        System.out.println("Enter product id : ");
        int pid=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter product name : ");
        String pname=sc.nextLine();
        System.out.println("Enter product quantity : ");
        int qt=sc.nextInt();
        System.out.println("Enter product price : ");
        double pprice=sc.nextDouble();
        Product pro=new Product(pid, pname, qt, pprice);
        inv.put(pid, pro);

        System.out.println("Product added! ");

    }

    public void updatep()
    {
        System.out.println("Enter product id : ");
        int pid=sc.nextInt();
        sc.nextLine();
        Product pro=inv.get(pid);
        if(pro!=null)
        {
            System.out.println("Enter product name : ");
            String pname=sc.nextLine();
            pro.setProductName(pname);
            System.out.println("Enter product quantity : ");
            int qt=sc.nextInt();
            pro.setQuantity(qt);
            System.out.println("Enter product price : ");
            double pprice=sc.nextDouble();
            pro.setPrice(pprice);
            
            System.out.println("Product Updated! ");
        }
        else{
            System.out.println("No such Product");
        }

    }

    public void deletep()
    {
        System.out.println("Enter product id : ");
        int pid=sc.nextInt();
        if(inv.containsKey(pid))
        {
            inv.remove(pid);
            System.out.println("product deleted");
        }
        else{
            System.out.println("No such product");
        }
    }

    public void display()
    {
        if(inv.isEmpty())
        {
            System.out.println("Its empty! ");
        }
        else
        {
        System.out.println("ProductId\tProductName\tQuantity\tPrice");
        for(Product p:inv.values())
            {
            System.out.printf("%10d\t",p.getProductId());
            System.out.printf("%11s\t",p.getProductName());
            System.out.printf("%8d\t",p.getQuantity());
            System.out.printf("%10f\t",p.getPrice());
            System.out.println();
        }
        }   
    }
}
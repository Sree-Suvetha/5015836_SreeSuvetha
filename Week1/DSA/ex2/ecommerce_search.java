import java.util.*;
public class ecommerce_search {
    public static void main(String args[])
{
    Ecommerce e=new Ecommerce();
    e.menu();
}
}

class Product
{
    int productId;
    String productName;
    String category;
    public Product(int productId, String productName, String category) 
    {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    } 
}

class Ecommerce
{
    ArrayList<Product> prod_arr=new ArrayList<>();
    Scanner sc=new Scanner(System.in);

    public void menu()
    {
        System.out.println("\t\t\tEcommerce platform search function\n");
        int ch;
        do{
            System.out.println("\n1.Add\n2.Linear Search\n3.Binary Search\n4.Exit\nEnter your choice: ");
            ch=sc.nextInt();
            switch (ch) {
            case 1:
            {
                addp();
                break;
            }
            case 2:
            {
                linear();
                break;
            }
            case 3:
            {
                binary();
                break;
            }
            case 4:
            {
                return;
            }
            default:
            {
                System.out.println("Enter a valid choice! ");
                break;
            }
        }
                
        }while(ch!=4);
    }

    public void addp()
    {
        System.out.println("Enter product id : ");
        int pid=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter product name : ");
        String pname=sc.nextLine();
        System.out.println("Enter product category : ");
        String pcategory=sc.nextLine();
        Product pro=new Product(pid, pname, pcategory);
        prod_arr.add(pro);

        System.out.println("Product added! ");

    }

    public void linear()
    {
        System.out.println("Enter product id : ");
        int pid=sc.nextInt();
        int flag=0;
        for(int i=0;i<prod_arr.size();i++)
        {
            if(pid==prod_arr.get(i).getProductId())
            {
                System.out.println("Product found!\nId: "+prod_arr.get(i).getProductId()+
                "\nname: "+prod_arr.get(i).getProductName()+"\nCategory: "+prod_arr.get(i).getCategory());
                flag=1;
                break;
            }
        }
        if(flag==0)
        System.out.println("Product not found!");
        
    }

    public void binary()
    {
        Collections.sort(prod_arr,Comparator.comparingInt(Product::getProductId));
        System.out.println("Enter product id : ");
        int pid=sc.nextInt();
        int flag=0,mid,start=0,end=prod_arr.size()-1;
        while(start<=end)
        {
            mid=(start+end)/2;
            if(pid==prod_arr.get(mid).getProductId())
            {
                System.out.println("Product found!\nId: "+prod_arr.get(mid).getProductId()+
                "\nname: "+prod_arr.get(mid).getProductName()+"\nCategory: "+prod_arr.get(mid).getCategory());
                flag=1;
                break;
            }
            else if(pid>prod_arr.get(mid).getProductId())
            {
                start=mid+1;
            }
            else
            {
                end=mid-1;
            }
        }
        if(flag==0)
        {
            System.out.println("Product not found!");
        }

    }

       
}

import java.util.*;
public class sorting_orders{
    public static void main(String args[])
{
    Sort s=new Sort();
    s.menu();
}
}

class Order
{
    int orderId;
    String customerName;
    double totalprice;

    public Order(int orderId, String customerName, double totalprice)
    {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalprice = totalprice;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public double getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
    
}

class Sort
{
    ArrayList<Order> arr=new ArrayList<>();
    Scanner sc=new Scanner(System.in);

    public void menu()
    {
        System.out.println("\t\t\tSorting Customer Orders\n");
        int ch;
        do{
            System.out.println("\n1.Add\n2.Bubble Sort\n3.Quick Sort\n4.display\n5.Exit\nEnter your choice: ");
            ch=sc.nextInt();
            switch (ch) {
            case 1:
            {
                addorder();
                break;
            }
            case 2:
            {
                bubble();
                break;
            }
            case 3:
            {
                quickSort(0,arr.size()-1);
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

    public void addorder()
    {
        System.out.println("Enter order id : ");
        int oid=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter customer name : ");
        String name=sc.nextLine();
        System.out.println("Enter total price : ");
        double price=sc.nextDouble();
        Order o=new Order(oid, name, price);
        arr.add(o);
        System.out.println("Order added! ");
    }

    public void bubble()
    {
       
       int i,j;
       boolean swapped;
       for(i=0;i<arr.size()-1;i++)
       {
            swapped=false;
            for(j=0;j<arr.size()-i-1;j++)
            {
                if(arr.get(j).getTotalprice()>arr.get(j+1).getTotalprice())
                {

                    Order temp = arr.get(j);
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1,temp);
                    swapped=true;
                }
            }
            if(swapped==false)
            break;
       }
        
    }

    public void quickSort(int low, int high) 
    {
        if (low < high) {
            int pivotIndex = partition(low, high);
            
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }
    
    private int partition(int low, int high) 
    {
        Order pivot = arr.get(high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr.get(j).getTotalprice() <= pivot.getTotalprice()) {
                i++;
                
                Order temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        
      
        Order temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        
        return i + 1;
    }
    

    public void display()
    {
        if(arr.size()==0)
        {
            System.out.println("Empty!!! ");
        }
        else
        {
            int i;
            System.out.println( "OrderId"+"\t    "+"CustomerName"+"\t"+"TotalPrice");
            for(i=0;i<arr.size();i++)
            {
                System.out.printf("%8d",arr.get(i).getOrderId());
                System.out.printf("%15s\t",arr.get(i).getCustomerName());
                System.out.printf("%18f\t",arr.get(i).getTotalprice());
                System.out.println();
            }
        }

    }

}  


        
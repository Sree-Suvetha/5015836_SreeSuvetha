import java.util.*;
class employee_management
{
    public static void main(String args[])
    {
        management m=new management();
        m.menu();
    }

}
class Employee
{
    int employeeId;
    String empName;
    String position;
    double salary;

    public Employee(int employeeId, String empName, String position, double salary) 
    {
        this.employeeId = employeeId;
        this.empName = empName;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

}
 
class management
{
    Employee[] arr=new Employee[10];
    int count=0;
    Scanner sc=new Scanner(System.in);
    
    public void menu()
    {
        System.out.println("\t\t\tEmployee management\n");
        int ch;
        do{
            System.out.println("\n1.Add\n2.Search\n3.Traverse\n4.Delete\n5.Exit\nEnter your choice: ");
            ch=sc.nextInt();
            switch (ch) {
            case 1:
            {
                addemp();
                break;
            }
            case 2:
            {
                search();
                break;
            }
            case 3:
            {
                traverse();
                break;
            }
            case 4:
            {
                delete();
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

    public void addemp()
    {
        if(count>=arr.length)
        {
            System.out.println("Cant add employees! ");
        }
        else
        {
            System.out.println("Enter employee id : ");
            int eid=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter employee name : ");
            String ename=sc.nextLine();
            System.out.println("Enter position : ");
            String pos=sc.nextLine();
            System.out.println("Enter salary : ");
            double salary=sc.nextDouble();
            Employee emp=new Employee(eid, ename, pos, salary);
            arr[count++]=emp;

            System.out.println("Employee added! ");
        }

    }

    public void search()
    {
        boolean found=false;
        
        if(count==0)
        {
            System.out.println("Empty ");

        }
        else
        {
            System.out.println("Enter employee id : ");
            int eid=sc.nextInt();
            for(int i=0;i<count;i++)
            {
            if(eid==arr[i].getEmployeeId())
                {
                System.out.println("\nId : "+arr[i].getEmployeeId()+"\nName : "
                +arr[i].getEmpName()+"\nPosition : "+arr[i].getPosition()+"\nSalary : "+arr[i].getSalary());
                found=true;
                break;
                }
            }
            if(found==false)
                {
                System.out.println("Employee not found");
                }
            }

    }

    public void delete()
    {
        boolean found=false;
    
        if(count==0)
        {
            System.out.println("Its empty! ");
        }
        else
        {
            System.out.println("Enter employee id : ");
            int eid=sc.nextInt();
            for(int i=0;i<count;i++)
            {
            if(eid==arr[i].getEmployeeId())
            {
                for(int j=i;j<count-1;j++)
                {
                    arr[j]=arr[j+1];
                }
                arr[count-1]=null;
                count--;
                found=true;
                System.out.println("Employee deleted");
                break;
            }
        }
        if(found==false)
        {
            System.out.println("Employee not found");
        }
        }
    }

    public void traverse()
    {
        if(count==0)
        {
            System.out.println("Its empty! ");
        }
        else
        {
            System.out.println("EmployeeId\tEmployeeName\tPosition\tSalary\n");
            for(int i=0;i<count;i++)
            {
                System.out.printf("%10d %17s %11s %13.2f",arr[i].getEmployeeId(),
                arr[i].getEmpName(),arr[i].getPosition(),arr[i].getSalary());
                System.out.println();
            }
        }
    }   
}

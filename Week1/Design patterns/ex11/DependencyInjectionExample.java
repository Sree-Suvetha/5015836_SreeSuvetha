interface CustomerRepository
{
    String findCustomerById(int id);
}

class CustomerRepoImpl implements CustomerRepository
{
    public String findCustomerById(int id)
    {
        if (id == 1) 
        {
            return "Customer 1";
        } 
        else if (id == 2) {
            return "Customer 2";
        } 
        else {
            return "Customer Not Found";
        }
    }
}

class CustomerService
{
    CustomerRepoImpl repo;
    public CustomerService(CustomerRepoImpl repo) {
        this.repo = repo;
    }

    public void print(int id) {
        String customer = repo.findCustomerById(id);
        System.out.println("Customer Details: " + customer);
    }
}

public class DependencyInjectionExample {
    public static void main(String args[])
    {
        CustomerRepoImpl repo=new CustomerRepoImpl();

        CustomerService service=new CustomerService(repo);
        service.print(1);
        service.print(4);
    }
}
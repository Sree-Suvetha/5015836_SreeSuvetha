import java.util.*;
class task_management
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        tmanager tm=new tmanager();
        Node head=null;
        int ch;
        System.out.println("\t\t\tTask Management System\n");
        try{do{
            System.out.println("\n1.Add\n2.Search\n3.Traverse\n4.Delete\n5.Exit\nEnter your choice: ");
            ch=sc.nextInt();
            switch (ch) {
            case 1:
            {
                System.out.println("Enter task id : ");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter task name : ");
                String name=sc.nextLine();
                System.out.println("Enter task Status : ");
                String sts=sc.nextLine();
                Task t=new Task(id, name, sts);
                head=tm.addtask(t, head);
                break;
            }
            case 2:
            {
                if(head==null)
                {
                    System.out.println("empty");
                }
                else{
                    System.out.println("Enter task id : ");
                    int id=sc.nextInt();
                    tm.search(id, head);
                }
                break;
            }
            case 3:
            {
                if(head==null)
                {
                    System.out.println("empty");
                }
                else
                {
                    tm.traverse(head);
                }
                break;
            }
            case 4:
            {
                if(head==null)
                {
                    System.out.println("empty");
                }
                else
                {
                    System.out.println("Enter task id : ");
                    int id=sc.nextInt();
                    head=tm.delete(id, head);
                }
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
        finally
        {
            sc.close();
        }
    }
}

class Task
{
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) 
    {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getStatus() {
        return status;
    }
    
}

class Node
{
    Task task;
    Node next;

    public Node(Task task) 
    {
        this.task = task;
        this.next = null;
    }
}

class tmanager
{
    public Node addtask(Task t,Node head)
    {
        Node newnode=new Node(t);
        if(head==null)
        {
            head=newnode;
        }
        else
        {
            Node cur=head;
            while(cur.next!=null)
            {
                cur=cur.next;
            }
            cur.next=newnode;
        }
        System.out.println("Task added! ");
        return head;

    }

    public void traverse(Node head)
    {
        Node cur=head;
        while(cur!=null)
        {
            System.out.println("Id : "+cur.task.getTaskId()+"\tName : "+cur.task.getTaskName()+"\tstatus : "
            +cur.task.getStatus());
            cur=cur.next;
        }
    }

    public void search(int id,Node head)
    {
        Node cur=head;
        int flag=0;
        while(cur!=null)
        {
            if(cur.task.getTaskId()==id)
            {
                flag=1;
                System.out.println("Id : "+cur.task.getTaskId()+"\tName : "+cur.task.getTaskName()+"\tstatus : "
                +cur.task.getStatus());
            }
            cur=cur.next;
        }
        if(flag==0)
        {
            System.out.println("Task not found");
        }
    }

    public Node delete(int id,Node head)
    {
        int flag=0;
        Node cur=head;
        Node previous=null;
            while(cur!=null)
            {
                if(cur.task.getTaskId()==id)
                {
                    if(previous==null)
                    {
                        head=cur.next;
                    }
                    else
                    {
                        previous.next=cur.next;
                    }
                    flag=1;
                    System.out.println("task deleted!");
                    break;
                }
                previous=cur;
                cur=cur.next;
            }
            if(flag==0)
            {
                System.out.println("Task not found");
            }
        
        return head;
    }
}
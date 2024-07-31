import java.util.*;
class library_management
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Search s=new Search();
        ArrayList<Book> arr=new ArrayList<>();
        System.out.println("\t\t\tLibrary Management System\n");
        int ch;
        String title;
        try {
        do{
            System.out.println("\n1.Add\n2.Linear Search\n3.Binary Search\n4.Exit\nEnter your choice: ");
            ch=sc.nextInt();
            sc.nextLine();
            switch (ch) {
            case 1:
            {
                System.out.println("Enter Book id : ");
                int bid=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter title : ");
                String t=sc.nextLine();
                System.out.println("Enter author : ");
                String a=sc.nextLine();
                s.addbook(arr,bid,t,a);
                break;
            }
            case 2:
            {
                if(arr.size()==0)
                {
                    System.out.println("Empty!!! ");
                }
                else
                {
                    System.out.println("Enter title : ");
                    title=sc.nextLine();
                    Book book=s.linear(arr,title);
                    if(book!=null)
                    {
                        System.out.println("Id : "+book.getBookId()+"\nTitle : "+book.getTitle()+"\nAuthor : "+book.getAuthor());
                    }
                    else
                    {
                        System.out.println("Not found");
                    }
                }
                break;
            }
            case 3:
            {
                if(arr.size()==0)
                {
                    System.out.println("Empty!!! ");
                }
                else
                {
                    System.out.println("Enter title : ");
                    title=sc.nextLine();
                    Book book=s.binary(arr,title);
                    if(book!=null)
                    {
                        System.out.println("Id : "+book.getBookId()+"\nTitle : "+book.getTitle()+"\nAuthor : "+book.getAuthor());
                    }
                    else
                    {
                        System.out.println("Not found");
                    }
                }
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
        finally {
            sc.close();
        }
    }
        
        
}


class Book
{
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) 
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
    public int getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

}

class Search
{
    public void addbook(ArrayList<Book> arr,int bid,String t,String a)
    {
        Book b=new Book(bid, t, a);
        arr.add(b);
    }

    public Book linear(ArrayList<Book> arr,String t)
    {
            for(Book b:arr)
            {
                if(t.equals(b.getTitle()))
                return b;
            }
            return null;
    }

    public Book binary(ArrayList<Book> arr,String t)
    {
        Collections.sort(arr,Comparator.comparing(Book::getTitle));
        int mid,start=0,end=arr.size()-1;
        while(start<=end)
        {
            mid=(start+end)/2;
            if(t.equalsIgnoreCase(arr.get(mid).getTitle()))
            {
                return arr.get(mid);
            }
            else if(t.compareTo(arr.get(mid).getTitle())>0)
            {
                start=mid+1;
            }
            else
            {
                end=mid-1;
            }
        }
        
            return null;
    }

}


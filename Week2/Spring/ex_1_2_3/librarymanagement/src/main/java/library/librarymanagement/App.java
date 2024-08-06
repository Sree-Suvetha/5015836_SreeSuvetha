package library.librarymanagement;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.*;

public class App 
{
    public static void main( String[] args )
    {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        // Ex 2
        BookService bookService=context.getBean(BookService.class);
        bookService.serviceMethod();
        bookService.printBookRepository();
        context.close();
    }
}

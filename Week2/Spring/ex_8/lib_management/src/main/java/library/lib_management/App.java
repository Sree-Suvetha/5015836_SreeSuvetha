package library.lib_management;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.BookService;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService service=context.getBean(BookService.class);
        service.displayBookRepository();
        context.close();
    }
}

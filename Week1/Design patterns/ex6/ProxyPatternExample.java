import java.util.*;

interface Image
{
    void display();
}

class RealImage implements Image
{
    public RealImage()
    {
        loadImage();
    }

    private void loadImage() {
        System.out.println("Image downloaded from remote server");
    }

    public void display()
    {
        System.out.println("Displayed Image");
    }
}
class ProxyImage implements Image
{
    private static final Map<String, RealImage> imageCache = new HashMap<>();
    RealImage image;
    private String user;
    ProxyImage(String user)
    {
        this.user=user;
    }

    public void display()
    {
        if (user.equals("admin")) {
            if (!imageCache.containsKey(user)) {
                image = new RealImage();
                imageCache.put(user, image);
            } 
            else {
                image = imageCache.get(user);
            }
            image.display();
        }
        else{
            System.out.println("Access Denied");
        }

    }
}
public class ProxyPatternExample {
    public static void main(String args[])
    {
        Image img=new ProxyImage("admin");
        img.display();
        Image img2=new ProxyImage("admin");
        img2.display();

        Image img1=new ProxyImage("user");
        img1.display();
    }
}

abstract class Document 
{
    abstract void display();
}
class WordDocument extends Document 
{
    void display() 
    {
        System.out.println("Word Document");
    }
}

class PdfDocument extends Document 
{
    void display() 
    {
        System.out.println("PDF Document");
    }
}

class ExcelDocument extends Document 
{
    void display() 
    {
        System.out.println("Excel Document");
    }
}

abstract class DocumentFactory
{
    abstract Document createDocument();
}

class WordFactory extends DocumentFactory
{
    Document createDocument()
    {
        return new WordDocument();
    }
}

class PdfFactory extends DocumentFactory
{
    Document createDocument()
    {
        return new PdfDocument();
    }
}

class ExcelFactory extends DocumentFactory
{
    Document createDocument()
    {
        return new ExcelDocument();
    }
}

class Factory
{
    public  DocumentFactory selectDoc(String type)
    {
        switch(type)
        {
            case "word":
            return new WordFactory();

            case "pdf":
            return new PdfFactory();

            case "excel":
            return new ExcelFactory();

            default:
            System.out.println("Invalid Document Type");
            return null;
        }
    }
}


public class FactoryMethodPatternExample 
{
    public static void main(String args[])
    {
        Factory obj=new Factory();
        
        DocumentFactory doc=obj.selectDoc("pdf");
        Document specific_doc=doc.createDocument();
        specific_doc.display();

        DocumentFactory doc1=obj.selectDoc("excel");
        Document specific_doc1=doc1.createDocument();
        specific_doc1.display();

        DocumentFactory doc2=obj.selectDoc("word");
        Document specific_doc2=doc2.createDocument();
        specific_doc2.display();
    }
}
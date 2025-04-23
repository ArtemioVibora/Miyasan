// *Lights up joint like Steve Jobs
//Oh boy, we're gonna do abstract classes
import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedList;

abstract class BackUpAbstract {

    int id;
    String vendor;
    String reference;
    LocalDateTime dateTime;
    String filePath;

    public BackUpAbstract(String filePath)
    {
        this.filePath = filePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getId()
    {
        return id;
    }

    public String vendor()
    {
        return vendor;
    }

    public String getReference()
    {
        return reference;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public String getFilePath()
    {
        return filePath;
    }

    abstract String formatter(String vendor, String reference, LocalDateTime dateTime);

    abstract String stringFromFileLoader(String fileName);

    abstract void createNewFile(String fileName);

    abstract boolean isEmpty(File file);

    abstract void fileWriter(LinkedList<String> listOfItems);
}

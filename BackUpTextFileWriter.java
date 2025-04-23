import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class BackUpTextFileWriter extends BackUpAbstract{

    public BackUpTextFileWriter(String filePath)
    {
        super(filePath);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setVendor(String vendor) {
        super.setVendor(vendor);
    }

    @Override
    public void setReference(String reference) {
        super.setReference(reference);
    }

    @Override
    public void setDateTime(LocalDateTime dateTime) {
        super.setDateTime(dateTime);
    }

    @Override
    public void setFilePath(String filePath) {
        super.setFilePath(filePath);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String vendor() {
        return super.vendor();
    }

    @Override
    public String getReference() {
        return super.getReference();
    }

    @Override
    public LocalDateTime getDateTime() {
        return super.getDateTime();
    }

    @Override
    public String getFilePath() {
        return super.getFilePath();
    }

    @Override
    String formatter(String vendor, String reference, LocalDateTime dateTime) {
        StringBuilder sbFormatter = new StringBuilder(reference + "\n" + vendor + "\n" + dateTime.toString() + "\n");
        return sbFormatter.toString();
    }

    @Override
    public String stringFromFileLoader(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        String line;

        if (file.exists())
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return sb.toString();
    }

    @Override
    void createNewFile(String fileName)
    {
        try
        {
            FileWriter writer = new FileWriter(filePath, false);
            writer.close();
            System.out.println("Success!");
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());

        }
    }

    @Override
    boolean isEmpty(File file)
    {
        if (file.length() == 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public void fileWriter(LinkedList<String> listOfItems)
    {
        try
        {

            FileWriter writer = new FileWriter(filePath, false);
            for (String i : listOfItems)
            {
                writer.write(i);
            }
            writer.close();
            System.out.println("Success!");
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());

        }
    }
}

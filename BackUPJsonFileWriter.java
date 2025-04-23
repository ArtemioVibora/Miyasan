import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class BackUPJsonFileWriter extends BackUpAbstract{

    public BackUPJsonFileWriter(String filePath)
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
        StringBuilder sbFormatter = new StringBuilder();
        sbFormatter
                .append(",\n")
                .append("  {\n")
                .append("     \"reference\": ").append("\"").append(reference).append("\",\n")
                .append("     \"vendor\": ").append("\"").append(vendor).append("\",\n")
                .append("     \"DateTime\": ").append("\"").append(dateTime).append("\"\n")
                .append("  }");
        return sbFormatter.toString();
    }

    String formatterPrecedence(String vendor, String reference, LocalDateTime dateTime) {
        StringBuilder sbFormatter = new StringBuilder();
        sbFormatter
                .append("  {\n")
                .append("     \"reference\": ").append("\"").append(reference).append("\",\n")
                .append("     \"vendor\": ").append("\"").append(vendor).append("\",\n")
                .append("     \"DateTime\": ").append("\"").append(dateTime).append("\"\n")
                .append("  }");
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

    public String formatterOpenCloseBrackets(String str)
    {
        String newStr = "";
        try
        {
            char[] tempCharArr1 = str.toCharArray();
//            for (int i = 0; i < tempCharArr1.length; i++)
//            {
//                if (tempCharArr1[i] == ']')
//                {
//                    tempCharArr1[i] = ' ';
//                }
//            }
            String tempStr = new String(tempCharArr1);
            newStr = tempStr.replace("[", "").replace("]","");

//            char[] tempCharArr2 = tempStr.toCharArray();
//
//            for (int j = 0; j < tempCharArr2.length; j++)
//            {
//                if (tempCharArr2[j] == '[')
//                {
//                    tempCharArr2[j] = ' ';
//                }
//            }



        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return newStr.strip();
    }

    @Override
    void fileWriter(LinkedList<String> listOfItems) {
        listOfItems.addFirst("[\n  ");
        listOfItems.addLast("\n]");

        try
        {
            //FileWriter writer = new FileWriter(filePath, false);
            //ALRIGHT BufferedWriter satisfied
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(filePath, false)
                    )
            );
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

    @Override
    void createNewFile(String fileName)
    {
        try
        {
            //Screw it imma use BufferedWriter here just for the lols
            //FileWriter writer = new FileWriter(filePath, false);
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(fileName, false)
                    )
            );
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

//    int counter(LinkedList<String> listOfItems)
//    {
//        int tracker = 0;
//        for (String i : listOfItems)
//            tracker++;
//
//        return tracker;
//    }
}

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

//March 31 12:14 am
//Laying the groundwork first before SQL
//Will apply my very own seed protocol

public class App {

    private static String json;
    private static String txt;
    private int type;

    public static LinkedList<String> temporaryMemory = new LinkedList<>();
    public static LinkedList<String> temporaryJsonMemory = new LinkedList<>();


    public static Displays display = new Displays();
    public static SQL_Sorter sorter = new SQL_Sorter();
    public static Prompter prompt = new Prompter();
    public static BackUpTextFileWriter textFileWriter = new BackUpTextFileWriter(txt);
    public static BackUPJsonFileWriter textFileJson = new BackUPJsonFileWriter(json);


    public static InputStreamReader streamReader = new InputStreamReader(System.in);
    public static BufferedReader reader = new BufferedReader(streamReader);

    public App(int type)
    {
        this.type = type;
    }

    //Helpline command line prompt
    public static void HelpLine()
    {
        int input = 0;

        while (input != 3)
        {
            try
            {
                System.out.print("Input: ");
                input = Integer.parseInt(reader.readLine());
                switch(input)
                {
                    case 1:
                        display.HelpMessages(0);
                        break;
                    case 2:
                        display.HelpMessages(1);
                        break;
                    case 3:
                        break;
                    default:
                        display.NumberError();
                        break;
                }
            }
            catch (Exception e)
            {
                display.CharacterInputError();
            }
        }
    }

    //This function lets the user proceed if he or she or they are satisfied with the answer.
    public void proceed(String vendor, String reference, LocalDateTime dateTime, boolean isIDValid)
    {
        String input;
        display.displayInput(vendor, reference, dateTime.toString());
        System.out.print("Proceed(Y/N): ");
        try
        {
            if (isIDValid) {
                input = reader.readLine();
                switch (input.toLowerCase()) {
                    case "y":
                        //sorter.addEntry(id, vendor, reference, dateTime);
                        temporaryMemory.add(textFileWriter.formatter(vendor, reference, dateTime));
                        sorter.addEntry(vendor, reference, dateTime);
                        if (temporaryJsonMemory.isEmpty()) {
                            temporaryJsonMemory.add(textFileJson.formatterPrecedence(vendor, reference, dateTime));
                        } else {
                            temporaryJsonMemory.add(textFileJson.formatter(vendor, reference, dateTime));
                        }
                        break;
                    case "n":
                        System.out.println("Loading back to main menu");
                        break;
                    default:
                        display.GenericError();
                        break;
                }
            }
            else
            {
                display.RedundantIDError();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //type 1 == debugging mode
    //type 2 == automated mode
    public static void loadFile(int type)
    {
        int input;
        String newJSONFileName;
        String existingJSONFile;

        String newTextFileName;
        String existingTextFileName;
        boolean flag = true;
        boolean isEmpty;
        boolean isEmpty2;

        //Debugging mode, I was checking if the program can read my file
        //I am choosing to keep it because if I want to continue this project
        //I am going to make this into one of its feautres in which it lets
        //the user to create their own json and txt file.
        if (type == 1) {
            while (flag) {
                display.DisplayOptions(2);
                try {
                    System.out.print("Enter Input: ");
                    input = Integer.parseInt(reader.readLine());

                    Thread.sleep(1000);
                    display.displayWarning(3);
                    Thread.sleep(1000);
                    switch (input) {
                        case 1:
                            System.out.print("What is the existing JSON file name: ");
                            existingJSONFile = reader.readLine();
                            System.out.println(existingJSONFile);
                            File jsonFile = new File(existingJSONFile);
                            if (jsonFile.exists()) {
                                json = existingJSONFile;
                                textFileJson.setFilePath(getJson());
                                String tfj = textFileJson.stringFromFileLoader(getJson());
                                String reformattedTFJ = textFileJson.formatterOpenCloseBrackets(tfj);
                                System.out.println(reformattedTFJ);
                                temporaryJsonMemory.add(reformattedTFJ);
                                System.out.println("Success!");
                            } else {
                                display.CouldNotFindFileError();
                                display.displayWarning(3);
                                loadFile(1);
                            }
                            System.out.print("What is the existing Text File name: ");
                            existingTextFileName = reader.readLine();
                            File txtFile = new File(existingTextFileName);
                            if (txtFile.exists()) {
                                txt = existingTextFileName;
                                textFileWriter.setFilePath(getTxt());
                                String tfw = textFileWriter.stringFromFileLoader(getTxt());
                                System.out.println(tfw);
                                temporaryMemory.add(tfw);
                                System.out.println("Success!");
                            } else {
                                display.CouldNotFindFileError();
                                display.displayWarning(3);
                                loadFile(1);
                            }
                            flag = false;
                            break;
                        case 2:
                            System.out.print("What is the new json file name: ");
                            newJSONFileName = reader.readLine();
                            System.out.print("What is the new txt file name: ");
                            newTextFileName = reader.readLine();
                            setJson(newJSONFileName);
                            setTxt(newTextFileName);
                            flag = false;
                            break;
                        default:
                            break;
                    }
                } catch (FileNotFoundException fnfe) {
                    System.out.println(fnfe.getMessage());
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        //Automatic loading
        else if (type == 2)
        {
            try {
                existingJSONFile = "receipt.json";
                json = existingJSONFile;
                textFileJson.setFilePath(getJson());
                File jsonFile = new File(existingJSONFile);
                isEmpty = textFileJson.isEmpty(jsonFile);
                if (jsonFile.exists())
                {
                    Thread.sleep(2000);
                    display.displayWarning(5);
                    Thread.sleep(2000);
                    if (!isEmpty)
                    {
                        String tfj = textFileJson.stringFromFileLoader(getJson());
                        String reformattedTFJ = textFileJson.formatterOpenCloseBrackets(tfj);
                        display.displayFile(existingJSONFile);
                        System.out.println(reformattedTFJ);
                        temporaryJsonMemory.add(reformattedTFJ);
                        System.out.println("Loaded Successfully!");
                    }
                    else
                    {
                        System.out.println("File is empty");
                    }
                }
                else
                {
                    textFileJson.createNewFile(existingJSONFile);
                    System.out.println("File was not found");
                    System.out.printf("There file was created under the name: %s", existingJSONFile);
                }

                existingTextFileName = "receipt.txt";
                txt = existingTextFileName;
                File txtFile = new File(existingTextFileName);
                textFileWriter.setFilePath(getTxt());
                isEmpty2 = textFileWriter.isEmpty(txtFile);
                if (txtFile.exists())
                {
                    if (!isEmpty2)
                    {
                        String tfw = textFileWriter.stringFromFileLoader(getTxt());
                        display.displayFile(existingTextFileName);
                        System.out.println(tfw);
                        temporaryMemory.add(tfw);
                        System.out.println("Loaded Successfully!");
                    }
                    else
                    {
                        System.out.println("File is empty");
                    }
                }
                else
                {
                    textFileWriter.createNewFile(existingJSONFile);
                    System.out.println("File was not found");
                    System.out.printf("There file was created under the name: %s", existingTextFileName);
                }
                Thread.sleep(2000);
                System.out.println();
                System.out.println("File/s has been loaded!");
                System.out.println("Irashaimase!");
                System.out.println();
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }
    }

    public void CommandLine()
    {

        int input = 0;

        boolean doesIDNotExist;

        int id; //IDRECIEPTS
        String reference;
        String vendor;
        //This for the function of edit entry of sorter
        String newReference;

        //For date time
        int year;
        int month;
        int day;
        int hour;
        int minute;
        LocalDateTime dateTime;

        boolean flag = true;

        display.NameOfProgram();
        loadFile(getType());
        while(flag) {

            display.TitleCard();
            display.DisplayOptions(0);

            try {
                Thread.sleep(1000);
                System.out.println();
                display.displayWarning(2);
                System.out.println();
                System.out.print("Enter Input: ");
                input = Integer.parseInt(reader.readLine());
                switch (input)
                {
                    case 1:
                        System.out.println("Adding Entry");
                        System.out.println();
                        Thread.sleep(1000);
                        display.displayWarning(0);
                        System.out.println();
                        Thread.sleep(1000);

                        //id = prompt.promptID();
                        vendor = prompt.promptVendor();
                        reference = prompt.promptReference(0);
                        year = prompt.promptYear();
                        month = prompt.promptMonths();
                        day = prompt.promptDay(month);
                        hour = prompt.promptHours();
                        minute = prompt.promptMinute();
                        dateTime = LocalDateTime.of(year, month, day, hour, minute);
                        display.displayWarning(2);
                        doesIDNotExist = sorter.isRefValid(reference);
                        proceed(vendor, reference, dateTime, doesIDNotExist);
                        break;
                    case 2:
                        display.displayWarning(4);
                        System.out.println("Finding Entry");
                        reference = prompt.promptReference(0);
                        sorter.findEntry(reference);
                        break;
                    case 3:
                        display.displayWarning(4);
                        System.out.println("Editing Entry");
                        reference = prompt.promptReference(0);
                        newReference = prompt.promptReference(1);
                        sorter.editEntry(reference, newReference);
                        break;
                    case 4:
                        display.displayWarning(4);
                        System.out.println("Deleting Entry");
                        reference = prompt.promptReference(0);
                        sorter.deleteEntry(reference);
                        break;
                    case 5:
                        System.out.println("Backing Entry into a txt.file");
                        textFileWriter.fileWriter(temporaryMemory);
                        break;
                    case 6:
                        System.out.println("Backing Entry into a JSON file");
                        textFileJson.fileWriter(temporaryJsonMemory);
                        break;
                    case 7:
                        flag = false;
                        break;
                    case 8:
                        display.DisplayOptions(1);
                        HelpLine();
                        break;
                    //debug
                    case 9:
                        System.out.println(temporaryJsonMemory);
                        System.out.println(getJson());
                        break;
                    default:
                        display.GenericError();
                        break;
                }
            }
            catch (IOException ioe)
            {
                System.out.println(ioe.getMessage());
                display.CharacterInputError();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void setTxt(String txt)
    {
        App.txt = txt;
    }

    public static void setJson(String json)
    {
        App.json = json;
    }

    public static String getTxt()
    {
        return txt;
    }

    public static String getJson()
    {
        return json;
    }

    public int getType()
    {
        return type;
    }
}

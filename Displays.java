import java.time.LocalDateTime;

public class Displays {

    public void DisplayOptions(int type)
    {
        if (type == 0)
        {
            System.out.println();
            System.out.println("====================================");
            System.out.println("<1> Add Entry");
            System.out.println("<2> Find Entry");
            System.out.println("<3> Edit Entry");
            System.out.println("<4> Delete Entry");
            System.out.println("<5> Back-up to txt file");
            System.out.println("<6> Back-up to other format");
            System.out.println("<7> Exit Program");
            System.out.println("<8> help on how to use the program");
            System.out.println("<9> Debugging Mode");
            System.out.println("===================================");
        }
        else if (type == 1)
        {
            System.out.println();
            System.out.println("====================================");
            System.out.println("<1> Military Time Table");
            System.out.println("<2> How to use this app");
            System.out.println("<3> Back to Main app");
            System.out.println("====================================");
        }
        else if (type == 2)
        {
            System.out.println();
            System.out.println("====================================");
            System.out.println("<1> Load Existing File ");
            System.out.println("<2> New File");
            System.out.println("====================================");
            System.out.println();
        }
    }

    public void NameOfProgram()
    {
        try {
            Thread.sleep(3000);
            System.out.println();
            System.out.println("M   M   M  IIIII  Y   Y    A          SSSSSS    A    N     N");
            System.out.println("Mm  M  mM    I    Y   Y   A A        S         A A   Nn    N");
            System.out.println("M m M m M    I     y y   A   A  ===  SSSSSS   A   A  N  n  N");
            System.out.println("M  mMm  M    I      Y    AAAAA             S  AAAAA  N    nN");
            System.out.println("M   M   M  IIIII    Y    A   A       SSSSSS   A    A N     N");
            System.out.println();
            Thread.sleep(3000);
        }
        catch(Exception e)
        {

        }
    }

    public void TitleCard()
    {
        System.out.println("====================================");
        System.out.println("           RECEIPT SAVER            ");
        System.out.println("====================================");
    }

    public void displayFile(String fileName)
    {
        System.out.println("----------------------------------");
        System.out.printf("   FILE NAME: %s\n", fileName);
        System.out.println("-----------------------------------");
    }

    public void displaySearchItem(String reference, String vendor, String localDateTime)
    {
        System.out.printf("Reference: %s\n", reference);
        System.out.printf("Vendor: %s\n", vendor);
        System.out.printf("Date entered: %s\n", localDateTime);
    }

    public void displayInput(String vendor, String reference, String localDateTime)
    {
        try
        {
            Thread.sleep(1000);
            System.out.println("--------------------------------");
            System.out.println("Reference: " + vendor);
            System.out.println("Vendor: " + reference);
            System.out.println("Date Time: " + localDateTime);
            System.out.println("--------------------------------");
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Help messages
    public void HelpMessages(int type)
    {
        //For type 0
        double hour = 0;
        double militaryHour = 0;
        String timeStamp = "am";
        //table
        if (type == 0)
        {
            System.out.println("If the user doesn't know military standard time");
            System.out.println("Here is a table: ");
            System.out.println("============================================");
            for (int i = 0; i < 24; i++)
            {
                System.out.printf("|\t %.2f%s |\t %.2f \n", hour, timeStamp, militaryHour);
                hour++;
                militaryHour++;
                if (hour > 12)
                {
                    hour = 1;
                    timeStamp = "pm";
                }
            }
            System.out.println();
        }
        else if (type == 1)
        {
            System.out.println("==============================================");
            System.out.println("To enter entry: press 1");
            System.out.println("To find entry: press 2");
            System.out.println("To edit entry: press 3");
            System.out.println("To delete entry: press 4");
            System.out.println("To back up to text file: press 5");
            System.out.println("To back up to JSON file: press 6");
            System.out.println("To exit: press 7");
            System.out.println("===============================================");
            System.out.println();

        }
    }

    //Error messages
    public void MayaGcashError()
    {
        System.out.println();
        System.out.println("Error! Only Maya and Gcash are allowed");
        System.out.println("Please make sure that they are spelled correctly!");
        System.out.println("And please make sure to enter the given vendors: ");
        System.out.println();
        System.out.println("1. Maya");
        System.out.println("2. Gcash");
        System.out.println();
        System.out.println("Please try again!");
        System.out.println();
    }

    public void NumberError()
    {
        System.out.println("Error! Invalid Number!");
        System.out.println("Please try again!");
    }

    public void DayError(int type)
    {
        System.out.println("Error! Invalid Input!");
        //For month of february
        if (type == 0)
        {
            System.out.println("For the month of february: It is only limited to 29 days");
        }
        else if (type == 1)
        {
            System.out.println("For the months of April, June, September, and November: It is only limited to 30 days");
        }
        else if (type == 2)
        {
            System.out.println("Input limited only to 31 days");
        }
        System.out.println("Please Try Again!");
    }

    public void RedundantIDError()
    {
        System.out.println("ID is invalid, there is already an ID that exists!");
        System.out.println("Please make sure that ID is unique!");
        System.out.println("Please try again!");
    }

    public void rowCouldNotBeFound()
    {
        System.out.println("Error! Row could not be found");
    }

    public void CharacterInputError()
    {
        System.out.println("Error: Invalid Input!");
        System.out.println("Alphabetical Characters are not allowed");
        System.out.println("Please try again!");

    }

    public void CouldNotFindFileError()
    {
        System.out.println("Error: File could not be loaded");
    }

    public void GenericError()
    {
        System.out.println("Error! Invalid Input!");
    }

    //Warnings
    public void displayWarning(int type)
    {
        System.out.println();
        if (type == 0)
        {
            System.out.println("Warning: This is in military time");
            System.out.println("Please Proceed with caution");
            System.out.println("Make sure that the ID exist, otherwise, the application\nwill reject your input");
        }
        else if (type == 1)
        {
            System.out.println("Warning: If ID does not exist");
            System.out.println("Program will go back to main menu");
        }
        else if (type == 2)
        {
            System.out.println("Warning: Please be reminded that this program is only an instance of itself");
            System.out.println("Warning: When typing hours, it should be in military time");
            System.out.println("Warning: Gcash and Maya are the only available vendors at that time");
            System.out.println("Warning: Any input of ID if ID exists: If Adding Entry then it will be rejected");
        }
        else if (type == 3)
        {

            System.out.println("Warning: This product is a prototype");
            System.out.println("Warning: Failure to identify file will reload this options menu");
            System.out.println("Warning: Please make sure to spell it correctly, otherwise this options menu will reload");
        }
        else if (type == 4)
        {
            System.out.println("Warning: Make sure reference exists!");
        }

        else if (type == 5)
        {
            System.out.println("This mode is in accordance to the tech specs of Sir Lu");
            System.out.println("Therefore this program will automatically load the files!");
            System.out.println("Warning: If file does not exist, program will automatically create a file for you!");
            System.out.println("Loading the file!");
        }
        System.out.println();
    }
}

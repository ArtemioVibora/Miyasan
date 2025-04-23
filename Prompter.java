import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//What this class does is to give out the prompts and will act as a Filter class
public class Prompter {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Displays display = new Displays();
    //Filter functions
//    public int promptID()
//    {
//        int id = 0;
//        while(id <= 0)
//        {
//            try
//            {
//                System.out.print("Enter id number: ");
//                id = Integer.parseInt(reader.readLine());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//
//        }
//        return id;
//    }

    //This is assuming that we cannot use any vendor things other than GCash or Maya
    public String promptVendor()
    {
        String vendor = " ";

        while(!(vendor.equals("Gcash") || vendor.equals("Maya")))
        {
            System.out.print("Enter Vendor: ");
            try
            {
                vendor = reader.readLine();

                if(!(vendor.equals("Gcash") || vendor.equals("Maya")))
                {
                    display.MayaGcashError();
                }

            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }
        return vendor;
    }

    public String promptReference(int type)
    {
        String reference = "";
        try
        {
            if (type == 0)
            {
                System.out.print("Enter reference: ");
                reference = reader.readLine();
            }
            else if (type == 1)
            {
                System.out.print("Enter new reference: ");
                reference = reader.readLine();

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return reference;
    }

    //Filter functions for Time
    public int promptHours()
    {
        int hour = 0;
        //This is assuming that we will only have 24 hours of course
        while (hour < 1 || hour > 24)
        {
            try
            {
                System.out.print("Enter hour: ");
                hour = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return hour;
    }

    public int promptMinute()
    {
        int minute = 0;

        while(minute <= 0 || minute >= 60)
        {
            try
            {
                System.out.print("Enter minute: ");
                minute = Integer.parseInt(reader.readLine());
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        return minute;
    }

    public int promptYear()
    {
        int year = 0;

        while(year < 2025)
        {
            try
            {
                System.out.print("What year: ");
                year = Integer.parseInt(reader.readLine());
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        return year;
    }

    //Month
    public int promptMonths()
    {
        int month = 0;

        while (month <= 0 || month > 12)
        {
            try
            {
                System.out.print("Enter month: ");
                month = Integer.parseInt(reader.readLine());
            }
            catch (Exception e)
            {
                display.CharacterInputError();
            }
        }
        return month;
    }

    //Day with a parameter of month to determine if it is for the month of April, June, Sept or Nov or Feb which has
    //days that are less than 31 except for Feb which has days less than 30
    public int promptDay(int month)
    {
        int day = 0;
        if (month == 4 || month == 6 || month == 9 || month == 11)
        {
            while (day <= 0 || day > 30)
            {
                try
                {
                    System.out.print("What day: ");
                    day = Integer.parseInt(reader.readLine());
                    if (day <= 0 || day > 30)
                    {
                        display.DayError(1);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        else if (month == 2)
        {
            while (day <= 0 || day > 29)
            {
                try
                {
                    System.out.print("What day: ");
                    day = Integer.parseInt(reader.readLine());
                    if (day <= 0 || day > 29)
                    {
                        display.DayError(0);
                    }
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        else
        {
            while (day <= 0 || day > 31)
            {
                try
                {
                    System.out.print("What day: ");
                    day = Integer.parseInt(reader.readLine());
                    if (day <= 0 || day > 31)
                    {
                        display.DayError(2);
                    }
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }

        return day;
    }
}

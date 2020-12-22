package student;
import java.util.Calendar;

/*
 * @author Sofia
 * @version 1.0 (2020)
 */

public class Student
{
    // FINAL
    public static final String DEFAULT_FIRSTNAME = "FirstName";
    public static final String DEFAULT_LASTNAME = "LastName";
    public static final Gender DEFAULT_GENDER = Gender.MALE;
    public static final int YEAR_MIN = 1900;
    public static final int MONTH_MIN = 1;
    public static final int MONTH_MAX = 12;

    // VARIABLE
    private String firstName = null;
    private String lastName = null;
    private Gender gender = null;
    private int yearBorn;
    private int monthBorn;
    private int dayBorn;

    // OPTION TO MAKE IT CLEANER
    String space = " ";

    // CALENDER OF TODAY
    public static Calendar todayDate = Calendar.getInstance();

    // DEFAULT
    public Student()
    {
        this(DEFAULT_FIRSTNAME, DEFAULT_LASTNAME, DEFAULT_GENDER, todayDate.get(Calendar.YEAR), todayDate.get(Calendar.MONTH), todayDate.get(Calendar.DAY_OF_MONTH));
    }

    // WITH ARGS
    public Student(String pFirstName, String pLastName, Gender pGender, int pDayBorn, int pMonthBorn, int pYearBorn)
    {
        if (validateFirstName(pFirstName) && validateLastName(pLastName) && validateGender(pGender) && validateBirthday(pDayBorn, pMonthBorn, pYearBorn))
        {
            this.firstName = pFirstName;
            this.lastName = pLastName;
            this.gender = pGender;
            this.dayBorn = pDayBorn;
            this.monthBorn = pMonthBorn;
            this.yearBorn = pYearBorn;
        }
        else
        {
            this.firstName = DEFAULT_FIRSTNAME;
            this.lastName = DEFAULT_LASTNAME;
            this.gender = DEFAULT_GENDER;
            this.dayBorn = todayDate.get(Calendar.DAY_OF_WEEK);
            this.monthBorn = todayDate.get(Calendar.MONTH);
            this.yearBorn = todayDate.get(Calendar.YEAR);
        }
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public Gender getGender()
    {
        return this.gender;
    }

    public int getDayBorn()
    {
        return this.dayBorn;
    }

    public int getMonthBorn()
    {
        return this.monthBorn;
    }

    public int getYearBorn()
    {
        return this.yearBorn;
    }

    public int getAge()
    {
        int year = todayDate.get(Calendar.YEAR);
        int month = todayDate.get(Calendar.MONTH) + 1;
        int day = todayDate.get(Calendar.DAY_OF_WEEK);

        int age = yearBorn - year;

        if (month > monthBorn)
        {
            age -= 1;
        }
        else if (monthBorn == month && dayBorn > day)
        {
            age -= 1;
        }
        return age;
    }

    private void setFirstName(String pFirstName)
    {
        if (validateFirstName(pFirstName))
        {
            this.firstName = pFirstName;
        }
    }

    private void setLastName(String pLastName)
    {
        if (validateLastName(pLastName))
        {
            this.lastName = pLastName;
        }
    }

    private void setGender(Gender pGender)
    {
        if (validateGender(pGender))
        {
            this.gender = pGender;
        }
    }

    private void setBirthDay(int pDay, int pMonth, int pYear)
    {
        if (validateBirthday(pDay, pMonth, pYear))
        {
            this.dayBorn = pDay;
            this.monthBorn = pMonth;
            this.yearBorn = pYear;
        }
    }

    private static boolean validateFirstName(String pFirstName)
    {
        return pFirstName != null && !pFirstName.isEmpty();
    }

    private static boolean validateLastName(String pLastName)
    {
        return pLastName != null && !pLastName.isEmpty();
    }

    private static boolean validateGender(Gender pGender)
    {
        return pGender != null;
    }

    private static boolean validateBirthday(int pDay, int pMonth, int pYear)
    {
        int nbDayFebruary = 28;
        boolean dateIsValid = false;

        if (pYear % 4 == 0 && pYear % 100 != 0 || pYear % 400 == 0)
        {
            nbDayFebruary = 29;
        }

        if (pYear >= YEAR_MIN && pYear <= todayDate.get(Calendar.YEAR))
        {
            if (pMonth >= MONTH_MIN && pMonth <= MONTH_MAX)
            {
                switch (pMonth)
                {
                    case 1, 3, 5, 7, 8, 10, 12 ->
                            {
                                if (pDay >= 1 && pDay <= 31)
                                {
                                    dateIsValid = true;
                                }
                            }
                    case 2 ->
                            {
                                if (pDay >= 1 && pDay <= nbDayFebruary)
                                {
                                    dateIsValid = true;
                                }
                            }
                    case 4, 6, 9, 11 ->
                            {
                                if (pDay >= 1 && pDay <= 30)
                                {
                                    dateIsValid = true;
                                }
                            }
                }
            }
        }
        return dateIsValid;
    }

    private String monthToString(int pMonth)
    {
        String monthString = "";

        switch (pMonth)
        {
            case 1 -> monthString = "january";
            case 2 -> monthString = "february";
            case 3 -> monthString = "march";
            case 4 -> monthString = "april";
            case 5 -> monthString = "may";
            case 6 -> monthString = "june";
            case 7 -> monthString = "july";
            case 8 -> monthString = "august";
            case 9 -> monthString = "september";
            case 10 -> monthString = "october";
            case 11 -> monthString = "november";
            case 12 -> monthString = "december";
        }
        return monthString;
    }

    public String toStringDate()
    {
        return dayBorn + space + monthToString(monthBorn) + space + yearBorn;
    }

    @Override
    public String toString()
    {
        return firstName + space + lastName + " was born the " + dayBorn + monthToString(monthBorn) + yearBorn;
    }
}
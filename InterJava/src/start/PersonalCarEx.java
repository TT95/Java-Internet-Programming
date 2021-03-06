package start;

import java.util.GregorianCalendar;

import start.ex.ImpossibleDrive;
import start.ex.IncorrectDate;
import start.ex.IncorrectInput;
import start.ex.IncorrectLastName;
import start.ex.IncorrectName;

/**
 * Created by teo on 10/27/16.
 */
public class PersonalCarEx extends MyCarEx{

    private Gender gender;
    private String name;
    private String lastName;
    private GregorianCalendar lastTripDate;
    private GregorianCalendar purchaseDate;


    public PersonalCarEx(String input) throws IncorrectInput {
        super(input); 
        try {
        	String[] arguments = input.split(";");
            lastTripDate = new GregorianCalendar(0,0,0);
            gender = Gender.toValue(arguments[3]);
            String providedName = arguments[4];
            String providedLastName = arguments[5];
            purchaseDate = parseDate(arguments[6]);
            this.lastName = parseLastName(providedLastName);
            this.name = parseName(providedName);
        } catch (IndexOutOfBoundsException ex) {
           throw new IncorrectInput(input, "Incorrect number of arguments!");
        }

    }


    private GregorianCalendar parseDate(String date) throws IncorrectDate {
        String[] dateArr = date.split("/");
        if (dateArr.length != 3) {
            throw new IncorrectDate(date, "Date argument incorrect!");
        }
        return new GregorianCalendar(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[2]));
    }

    private String parseLastName(String lastName) throws IncorrectLastName {
        String modifiedLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        if ( !lastName.matches(".*\\d+.*") && modifiedLastName.equals(lastName)) {
            return lastName;
        }
        throw new IncorrectLastName(lastName, "Last name must start with capital letter and must not contain numbers!");
    }

    private String parseName(String name) throws IncorrectName {
        if (PopularNames.isCorrectName(name, gender)) {
            return name;
        } else {
            throw new IncorrectName(name, "Given name is not popular!");
        }
    }

    @Override
    public void startTrip(double tripDistance) throws ImpossibleDrive{
        lastTripDate = new GregorianCalendar();
        super.startTrip(tripDistance);
    }

    public void startTrip(double tripDistance, int year, int month, int day) throws IncorrectDate, ImpossibleDrive {
        GregorianCalendar lastTripDateInput = new GregorianCalendar(year, month-1, day);
        if (lastTripDateInput.compareTo(purchaseDate) < 0) {
            throw new IncorrectDate("Date of travel is earlier then purchase date");
        } else {
            lastTripDate = lastTripDateInput;
            super.startTrip(tripDistance);
        }

    }

    @Override
    public String toString() {
        return super.toString()
                + "gender: " + gender + System.lineSeparator()
                + "name: " + name + System.lineSeparator()
                + "surname: " + lastName + System.lineSeparator()
                + "purchaseDate: " + purchaseDate.getTime() + System.lineSeparator()
                + "lastTripDate: " + lastTripDate.getTime() + System.lineSeparator();

    }


    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public GregorianCalendar getLastTripDate() {
        return lastTripDate;
    }

    public GregorianCalendar getPurchaseDate() {
        return purchaseDate;
    }

}

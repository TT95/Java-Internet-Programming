package start.test;

import org.junit.Before;
import org.junit.Test;
import start.Gender;
import start.StudentData;
import start.ex.*;

import static org.junit.Assert.*;

/**
 * Created by teo on 11/1/16.
 */
public class StudentDataTest {

    @Test
    public void argumentsExample1() throws IncorrectInput {
        StudentData student = new StudentData(" M; john; Brown; 1989/03/20;2010/09/01");
        assertEquals("John", student.getFirstName());
    }

    @Test
    public void argumentsExample2() throws IncorrectInput {
        StudentData student = new StudentData("Male; John; Brown-Jones; 89/03/20;2010/09/01");
        assertEquals(Gender.MALE, student.getGender());
    }

    @Test
    public void argumentsExample3() throws IncorrectInput {
        StudentData student = new StudentData("F;Mary; Brown; 89/03/20;10/09/01");
        assertEquals("Brown", student.getLastName());
    }

    @Test (expected = IncorrectDate.class)
    public void argumentTest1() throws IncorrectInput {
        new StudentData("Male; John; Brown-Jones; 89/20;2010/09/01");
    }

    @Test (expected = IncorrectName.class)
    public void argumentTest2() throws IncorrectInput {
        new StudentData("Male; Josko; Brown-Jones; 89/03/20;2010/09/01");
    }

    @Test (expected = IncorrectLastName.class)
    public void argumentTest3() throws IncorrectInput {
        new StudentData("Male; John; BrownJones; 89/03/20;2010/09/01");
    }

    @Test (expected = IncorrectGender.class)
    public void argumentTest4() throws IncorrectInput {
        new StudentData("Bird; John; Brown-Jones; 89/03/20;2010/09/01");
    }

    @Test (expected = IncorrectDate.class)
    public void argumentTest5() throws IncorrectInput {
        new StudentData("Male; John; Brown-Jones; 89/20;1990/09/01");
    }
}
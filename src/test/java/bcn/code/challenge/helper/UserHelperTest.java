package bcn.code.challenge.helper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserHelperTest {

    private InputStream originalSystemIn;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    public void setUp() {
        originalSystemIn = System.in;
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testGetUserInputWithValidInput() {
        String input = "Hello123";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        String userInput = UserHelper.getUserInput();

        assertEquals("Hello", userInput);
    }

    @Test
    public void testGetUserInputWithEmptyInput() {
        String input = "\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        String userInput = UserHelper.getUserInput();

        assertEquals("", userInput);
    }

    @Test
    public void testGetUserInputWithSpecialCharacters() {
        String input = "Hello$%^123";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        String userInput = UserHelper.getUserInput();

        assertEquals("Hello", userInput);
    }
}
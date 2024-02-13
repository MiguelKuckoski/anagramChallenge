package bcn.code.challenge.service;

import bcn.code.challenge.helper.AnagramFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnagramServiceTest {

    private AnagramService anagramService;

    @BeforeEach
    public void setUp() {
        List<String> dictionary = Arrays.asList("listen", "silent", "enlist", "car", "arc", "banana", "anaban", "abba");
        AnagramFilter anagramFilter = new AnagramFilter();
        anagramService = new AnagramService(dictionary, anagramFilter);
    }

    @Test
    public void testProcessAnagramNotInFilter() {
        List<String> expectedAnagrams = Arrays.asList("silent", "enlist");
        List<String> result = anagramService.processAnagram("listen");

        assertEquals(expectedAnagrams, result);
        assertFalse(result.contains("listen"));
    }

    @Test
    public void testProcessAnagramWithFilter() {
        List<String> expectedAnagrams = Arrays.asList("silent", "enlist");
        anagramService.processAnagram("listen");
        List<String> result = anagramService.processAnagram("listen");

        assertEquals(expectedAnagrams, result);
        assertFalse(result.contains("listen"));
    }

    @Test
    public void testProcessAnagramWithEmptyInput() {
        List<String> result = anagramService.processAnagram("");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testProcessAnagramWithNoMatch() {
        List<String> result = anagramService.processAnagram("a");
        assertTrue(result.isEmpty());
    }

}

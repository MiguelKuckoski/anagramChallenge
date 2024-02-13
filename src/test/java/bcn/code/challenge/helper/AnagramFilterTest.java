package bcn.code.challenge.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnagramFilterTest {

    private AnagramFilter anagramFilter;

    @BeforeEach
    public void setUp() {
        anagramFilter = new AnagramFilter();
    }

    @Test
    public void testInsertFilterAndGetAnagrams() {
        List<String> anagrams = Arrays.asList("listen", "silent", "enlist");
        anagramFilter.insertFilter("listen", anagrams);

        List<String> retrievedAnagrams = anagramFilter.getAnagrams("listen");

        assertEquals(anagrams, retrievedAnagrams);
    }

    @Test
    public void testInsertFilterAndGetAnagramsWithEmptyList() {
        List<String> anagrams = Arrays.asList();
        anagramFilter.insertFilter("silent", anagrams);

        List<String> retrievedAnagrams = anagramFilter.getAnagrams("silent");

        assertEquals(anagrams, retrievedAnagrams);
    }

    @Test
    public void testInsertFilterAndGetAnagramsWithDuplicateInput() {
        List<String> anagrams1 = Arrays.asList("listen", "silent", "enlist");

        anagramFilter.insertFilter("listen", anagrams1);
        anagramFilter.insertFilter("silent", anagrams1);

        List<String> retrievedAnagrams1 = anagramFilter.getAnagrams("listen");
        List<String> retrievedAnagrams2 = anagramFilter.getAnagrams("silent");

        assertEquals(retrievedAnagrams1, retrievedAnagrams2);
    }

    @Test
    public void testGetAnagramsWithNonexistentInput() {
        List<String> retrievedAnagrams = anagramFilter.getAnagrams("nonexistent");
        assertEquals(null, retrievedAnagrams);
    }
}

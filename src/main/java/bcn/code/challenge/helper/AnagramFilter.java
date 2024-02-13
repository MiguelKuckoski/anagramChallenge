package bcn.code.challenge.helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFilter {

    private final Map<String, List<String>> filter;

    public AnagramFilter() {
        this.filter = new HashMap<>();
    }

    public void insertFilter(String input, List<String> anagrams) {
        String key = sortInput(input);
        filter.computeIfAbsent(key, k -> anagrams);
    }

    public List<String> getAnagrams(String input) {
        String key = sortInput(input);
        return filter.get(key);
    }

    private String sortInput(String input) {
        char[] charArray = input.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}

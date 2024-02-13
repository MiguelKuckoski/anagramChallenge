package bcn.code.challenge.service;

import bcn.code.challenge.helper.AnagramFilter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class AnagramService {

    private final List<String> dictionary;

    private final AnagramFilter anagramFilter;

    public AnagramService(List<String> dictionary, AnagramFilter anagramFilter) {
        this.dictionary = dictionary;
        this.anagramFilter = anagramFilter;
    }

    public List<String> processAnagram(String input) {
        List<String> outputAnagrams = anagramFilter.getAnagrams(input);

        if(outputAnagrams == null){
            outputAnagrams = findAnagrams(input, dictionary);
            anagramFilter.insertFilter(input, outputAnagrams);
        }
        outputAnagrams.remove(input);

        return outputAnagrams;
    }

    private List<String> findAnagrams(String userInput, List<String> dictionary) {
        List<String> input = List.of(userInput.split(""));
        List<String> possibleWords = findPossibleWords(input, dictionary);
        return matchAnagrams(input, possibleWords, new StringBuilder());
    }

    private List<String> matchAnagrams(List<String> input, List<String> possibleWords, StringBuilder anagramBuilder) {
        List<String> anagramFinal = new LinkedList<>();

        for(int i = 0; i< possibleWords.size(); i++) {
            String currentWord = possibleWords.get(i);
            List<String> checkLetters = new LinkedList<>(input);
            boolean matchWord = true;

            for (char letter : currentWord.toCharArray()) {
                if (!checkLetters.remove(Character.toString(letter))) {
                    matchWord = false;
                    break;
                }
            }

            if (matchWord) {
                StringBuilder newAnagramBuilder = new StringBuilder(anagramBuilder);
                newAnagramBuilder.append(currentWord).append(" ");
                if (checkLetters.isEmpty()) {
                    anagramFinal.add(newAnagramBuilder.toString().trim());
                } else if (i + 1 < possibleWords.size()) {
                    List<String> remainingWords = possibleWords.subList(i + 1, possibleWords.size());
                    anagramFinal.addAll(matchAnagrams(checkLetters, remainingWords, newAnagramBuilder));
                }
            }
        }
        return anagramFinal;
    }

    private List<String> findPossibleWords(List<String> input, List<String> dictionary) {
        return dictionary.parallelStream()
                .filter(word -> {
                    List<String> wordLetters = List.of(word.split(""));
                    return new HashSet<>(input).containsAll(wordLetters);
                })
                .toList();
    }
}

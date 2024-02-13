package bcn.code.challenge;

import bcn.code.challenge.helper.AnagramFilter;
import bcn.code.challenge.helper.FileHelper;
import bcn.code.challenge.helper.UserHelper;
import bcn.code.challenge.service.AnagramService;

import java.util.List;
import java.util.Optional;

public class Main {

    private static final String DICTIONARY_FILE = "/dictionary.txt";

    public static void main(String[] args) {
        Optional<List<String>> resourceList = FileHelper.readFile(DICTIONARY_FILE);
        if(resourceList.isPresent()) {
            List<String> dictionary = resourceList.get();
            AnagramService anagramService = new AnagramService(dictionary, new AnagramFilter());

            String userInput;
            do {
                userInput = UserHelper.getUserInput();
                if(!userInput.isEmpty()) {
                    List<String> anagrams = anagramService.processAnagram(userInput);

                    System.out.println("***** Anagrams for: " + userInput + " *****");
                    anagrams.forEach(System.out::println);
                }else {
                    System.out.println("Anagram service finished.");
                }
            } while(!userInput.isEmpty());

        } else {
            System.out.println("Failed to load dictionary.");
        }
    }
}
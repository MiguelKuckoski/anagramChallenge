package bcn.code.challenge.helper;

import bcn.code.challenge.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileHelper {

    private FileHelper() {

    }

    public static Optional<List<String>> readFile(String path) {
        URL resource = Main.class.getResource(path);

        if(resource != null) {
            try (InputStream inputStream = resource.openStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                List<String> dictionary = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    dictionary.add(line);
                }
                return Optional.of(dictionary);
            } catch (IOException e) {
                System.out.println("Error reading dictionary: " + e.getMessage());
            }
        } else {
            System.out.println("Dictionary resource not found.");
        }
        return Optional.empty();
    }
}

package org.example.exercise13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ReadFromUrl {

    private final List<String> words = new ArrayList<>();

    public ReadFromUrl() throws IOException {

        URL engWords = new URL("https://github.com/dwyl/english-words/blob/master/words.txt?raw=true");

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(engWords.openStream()));

        String tempLine;
        // läs så länge tempLine == null, då är allt slut
        while ((tempLine = bufferedReader.readLine()) != null) {
            // readLine() är påkopplat, eftersom det är 1 ord per rad så kan vi bara adda in sen läsa nästa rad
            words.add(tempLine);
        }

        // stäng I/O
        bufferedReader.close();

        System.out.println(words.size());


        // With toMap, we can indicate strategies for how to get the key and value for the map
        Map<Object, Object> wordsAndLength = words.stream()
                        .collect(Collectors.toMap(word -> word, count -> count.length()));

        wordsAndLength.forEach((k , v) -> System.out.println(k + " - letters: " + v));

        /*
        groupingBy()
        Type Parameter: This method takes two type parameters:

            T- It is the type of the input elements.

            K- It is the type the input elements to be converted.
            Parameters: This method accepts two mandatory parameters:

            Function- It is the property which is to be applied to the input elements.

            Classifier- It is used to map input elements into the destination map.
            Return value: It returns a collector as a map.
         */

        Map<Integer, List<String>> wordCount = words.stream()
                .collect(Collectors.groupingBy(word -> word.length()));
        wordCount.forEach((k, v) -> System.out.println("Length of word: " + k + " word: " + v.get(0)));

    }

    public static void main(String[] args) throws IOException {
        new ReadFromUrl();
    }
}

package com.bil481.app;

import java.util.ArrayList;

public class App {

    // This method dechiphers the given integer list into a sentence which consists of English letters.

    // integerList: Holds characters in their corresponding order in English alphabet (e.g. a => 1 or 27).
    // changeList: Holds values to add to or subtract from integerList.
    // wordCount: Holds word count.
    // wordLengths: Holds lengths of each word in order.
    public static String decipher(ArrayList<Integer> integerList, int[] changeList, int wordCount, int[] wordLengths) {

        // Some compatibility checks between parameters
        if (wordCount <= 0
         || integerList.size() <= 0
         || wordCount != wordLengths.length
         || integerList.size() != changeList.length
         || wordCount > integerList.size()) {
            return null;
        }

        for (int i = 0, sum = 0; i < wordCount; i++) {
            sum += wordLengths[i];
            if (sum > integerList.size()) {
                return null;
            }
        }

        ArrayList<Character> characterList = new ArrayList<>();
        
        // Deciphers the character
        for (int i = 0; i < integerList.size(); i++) {
            int sum = integerList.get(i) + changeList[i];
            
            // When the corresponding order of character is less than 1
            if (sum < 1) {
                return null;
            }

            char c = (char) (((sum - 1) % 26) + 97);
            characterList.add(c);
        }

        String string = "";

        // Assembles the characters into words
        for (int i = 0, p = 0; i < wordCount; i++) {
            for (int j = 0; j < wordLengths[i]; j++) {
                string += characterList.get(p);
                p++;
            }
            if (i != wordCount - 1) {
                string += " ";
            }
        }

        return string;
    }
}

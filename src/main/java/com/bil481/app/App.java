package com.bil481.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    // This method dechiphers the given integer list into a sentence which consists of English letters.

    // integerList: Holds characters in their corresponding order in English alphabet (e.g. a => 1 or 27).
    // changeArray: Holds values to add to or subtract from integerList.
    // wordCount: Holds word count.
    // wordLengths: Holds lengths of each word in order.
    public static String decipher(ArrayList<Integer> integerList, int[] changeArray, int wordCount, int[] wordLengths) {

        // Some compatibility checks between parameters
        if (wordCount <= 0
         || integerList.size() <= 0
         || wordCount != wordLengths.length
         || integerList.size() != changeArray.length
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
            int sum = integerList.get(i) + changeArray[i];
            
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

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {

            // Input 1
            String input1 = req.queryParams("input1");
            Scanner sc1 = new Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            ArrayList<Integer> integerList = new ArrayList<>();

            while (sc1.hasNext()) {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                integerList.add(value);
            }
            System.out.println(integerList);
            sc1.close();

            // Input 2
            String input2 = req.queryParams("input2");
            Scanner sc2 = new Scanner(input2);
            sc2.useDelimiter("[;\r\n]+");
            ArrayList<Integer> temp1 = new ArrayList<>();

            while (sc2.hasNext()) {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
                temp1.add(value);
            }

            int[] changeArray = new int[(temp1.size())];
            for (int i = 0; i < temp1.size(); i++) {
                changeArray[i] = temp1.get(i);
            }
            System.out.println(changeArray);
            sc2.close();

            // Input 3
            String input3 = req.queryParams("input3").replaceAll("\\s","");
            int wordCount = Integer.parseInt(input3);

            // Input 4
            String input4 = req.queryParams("input4");
            Scanner sc4 = new Scanner(input4);
            sc4.useDelimiter("[;\r\n]+");
            ArrayList<Integer> temp2 = new ArrayList<>();

            while (sc4.hasNext()) {
                int value = Integer.parseInt(sc4.next().replaceAll("\\s",""));
                temp2.add(value);
            }

            int[] wordLengths = new int[(temp2.size())];
            for (int i = 0; i < temp2.size(); i++) {
                wordLengths[i] = temp2.get(i);
            }
            System.out.println(wordLengths);
            sc4.close();

            
            String result = App.decipher(integerList, changeArray, wordCount, wordLengths);

            Map<String, String> map = new HashMap<>();
            map.put("result", result);

            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute", (rq, rs) -> {
            Map<String, String> map = new HashMap<>();
            map.put("result", "not computed yet!");

            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

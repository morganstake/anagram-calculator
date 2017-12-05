package com.cgi.anagramcalculator;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AnagramCalculator
{
    private String myWord = "";

    private ArrayList<String> myAnagrams;

    private static TreeMap<String, ArrayList<String>> ourDictionaryMap = initializeDictionary();

    public static TreeMap<String, ArrayList<String>> initializeDictionary()
    {
        TreeMap<String, ArrayList<String>> dictionary = new TreeMap<>();
        try
        {
            Path dictionaryPath = FileSystems.getDefault().getPath("src/main/resources/Dictionary.txt");
            List<String> rawlist = Files.readAllLines(dictionaryPath);
            for (String currentWord : rawlist)
            {
                String wordKey = getWordKey(currentWord);
                ArrayList<String> wordList = dictionary.get(wordKey);
                if (wordList == null)
                {
                    wordList = new ArrayList<String>();
                }
                wordList.add(currentWord);
                dictionary.put(wordKey, wordList);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return dictionary;
    }

    public void dumpDictionary(int length)
    {
        for (Map.Entry<String, ArrayList<String>> entry : ourDictionaryMap.entrySet())
        {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            if (value.size() > length)
            {
                System.out.println(key + " => " + value.size());

            }
        }
    }

    private static String getWordKey(String word)
    {
        char[] chars = word.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public ArrayList<String> lookupAnagrams(String word)
    {
        ArrayList<String> words = new ArrayList<>(ourDictionaryMap.get(getWordKey(word)));
        if (words != null)
        {
            if(! words.remove(word))
            {
                // Capitialization of Input may not be consistent with the returned Anagrams
                for (String currentWord : words)
                {
                    if (currentWord.equalsIgnoreCase(word))
                    {
                        words.remove(currentWord);
                        break;
                    }
                }
            }
            Collections.sort(words);
        }
        setWord(word);
        setAnagrams(words);
        return words;
    }

    public void calculateAnagrams(String word)
    {
        String response = "";
        setWord(word);
        try
        {
            ArrayList<String> anagrams = lookupAnagrams(word);
            if (anagrams != null)
            {
                response = anagrams.toString();
            }
        }
        catch (Exception e)
        {


        }
    }


    public String getWord()
    {
        return myWord;
    }

    public void setWord(String word)
    {
        myWord = word;
    }

    public ArrayList<String> getAnagrams()
    {
        if ( myAnagrams == null)
        {
            myAnagrams = new ArrayList<String>();
        }
        return myAnagrams;
    }

    public void setAnagrams(ArrayList<String> anagrams)
    {
        myAnagrams = anagrams;
    }

    public static void main(String[] args)
    {
        AnagramCalculator acs = new AnagramCalculator();
        acs.dumpDictionary(4);
        acs.lookupAnagrams("bares");
    }
}

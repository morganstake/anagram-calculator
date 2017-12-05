# anagram-calculator
ECS Anagram interview excercise
--



Anagram Calculator

This specification is for a REST-like service that will take a given word and, after confirming that it exists in a given dictionary, will return any anagrams of that word.

Application Requirements

The application must use the dictionary located here: https://users.cs.duke.edu/~ola/ap/linuxwords 
API specification

Request

GET /word/:word
Response

Returns `200 OK` if the word exists in the dictionary.
```
GET /word/glare

HTTP 200 OK

{
    "word": "glare",
    "anagrams": [
      "Alger",
      "lager",
      "large",
      "regal"
    ]
}
```

#Notes:

- You need only return anagrams that are single words. For example, if word is hear, "a her" is not an anagram.
- The anagrams returned must only come from the provided list of words.
- The list of words in anagrams must not contain the requested word itself.
- The list of words in anagrams must be sorted alphabetically.
- The list of words in anagrams must be returned in their original case.
- Returns 404 Not Found if the word does not exist in the dictionary, with the response body
```
GET /word/asdfasdf1234

HTTP 404 Not Found

{ "message" : "Couldn't find word asdfasdf1234" }
```
- Returns 404 Not Found if the word is not specified (GET /word). The response body here is irrelevant
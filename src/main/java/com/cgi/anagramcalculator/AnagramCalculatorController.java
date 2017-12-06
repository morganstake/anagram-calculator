package com.cgi.anagramcalculator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AnagramCalculatorController
{
    @RequestMapping(value="/word/{word}", method=RequestMethod.GET)
    public AnagramCalculator word(@PathVariable("word") String word)
    {
        AnagramCalculator acs = new AnagramCalculator();
        acs.calculateAnagrams(word);
        if (acs.getAnagrams().size() == 0)
        {
            throw new UnknownWordException(word);
        }
        return acs;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnknownWordException.class)
    public String handleUnknownWordException(final UnknownWordException e, final HttpServletRequest request)
    {
        return e.getMessage();
    }

    class UnknownWordException extends RuntimeException
    {
        String myMessage;
        private String ourMessageTemplate = "{ \"message\" : \"Couldn't find word %s\" }";

        public UnknownWordException(String word)
        {
            setMessage(String.format(ourMessageTemplate, word));
        }

        public String getMessage()
        {
            return myMessage;
        }

        public void setMessage(String message)
        {
            myMessage = message;
        }

        @Override
        public String toString()
        {
            return getMessage();
        }
    }
}

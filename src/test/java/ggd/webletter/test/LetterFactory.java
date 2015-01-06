package ggd.webletter.test;

import ggd.webletter.model.Closing;
import ggd.webletter.model.Letter;
import ggd.webletter.model.Person;
import ggd.webletter.model.Salutation;

public class LetterFactory {

    public static Letter create() {
        String body = "hello world";
        Salutation salutation = new Salutation("Hi");
        Closing closing = new Closing("Bye");
        Person receiver = new Person("receiver", "receiver address");
        Person sender = new Person("sender", "sender address");
        return Letter.create(sender, receiver, salutation, closing, body);
    }
}

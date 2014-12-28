package ggd.webletter.model;

import org.joda.time.LocalDate;

import javax.persistence.Id;
import java.util.Date;

public class Letter {

    @Id
    private String id;

    private Person sender;

    private Person receiver;

    private Salutation salutation;

    private Date date;

    private Closing closing;

    private String body;

    private Letter() {
    }

    public static Letter create(Person sender, Person receiver, Salutation salutation, Closing closing, String body) {
        return new Letter(sender, receiver, salutation, closing, new LocalDate(), body);
    }

    public static Letter createFromParams(String senderName, String senderAddress, String receiverName,
                                          String receiverAddress, String salutation, String body, String closing) {
        Person sender = new Person(senderName, senderAddress);
        Person receiver = new Person(receiverName, receiverAddress);
        return Letter.create(sender, receiver, salutation(salutation), closing(closing), body(body));
    }

    private static Salutation salutation(String salutation) {
        return new Salutation(salutation);
    }

    private static String body(String body) {
        return body.replaceAll("\r", "");
    }

    private static Closing closing(String closing) {
        return new Closing(closing);
    }

    private Letter(Person sender, Person receiver, Salutation salutation, Closing closing, LocalDate date, String body) {
        this.sender = sender;
        this.receiver = receiver;
        this.salutation = salutation;
        this.closing = closing;
        this.body = body;
        setDate(date);
    }

    public Person getSender() {
        return sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public String getBody() {
        return body;
    }

    public Salutation getSalutation() {
        return salutation;
    }

    public Closing getClosing() {
        return closing;
    }

    public LocalDate getDate() {
        return LocalDate.fromDateFields(date);
    }

    public void setDate(LocalDate date) {
        this.date = date.toDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

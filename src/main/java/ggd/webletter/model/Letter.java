package ggd.webletter.model;

import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Letter {

    @Id
    private String id;

    @AttributeOverrides({
            @AttributeOverride(name="name",column=@Column(name="senderName")),
            @AttributeOverride(name="address",column=@Column(name="senderAddress"))
            })
    @Embedded
    private Person sender;

    @AttributeOverrides({
            @AttributeOverride(name="name",column=@Column(name="receiverName")),
            @AttributeOverride(name="address",column=@Column(name="receiverAddress"))
    })
    @Embedded
    private Person receiver;

    @Column(columnDefinition = "date")
    private Date date;

    @AttributeOverrides({
            @AttributeOverride(name="text",column=@Column(name="salutation"))
    })
    @Embedded
    private Salutation salutation;

    @AttributeOverrides({
            @AttributeOverride(name="text",column=@Column(name="closing"))
    })
    @Embedded
    private Closing closing;

    private String body;

    @ManyToOne
    private Account account;

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
        this.id = UUID.randomUUID().toString();
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

    public void setAccount(Account account) {
        this.account = account;
    }
}

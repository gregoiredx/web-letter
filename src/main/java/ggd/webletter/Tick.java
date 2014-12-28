package ggd.webletter;

import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ticks")
public class Tick {

    public Tick() {
        id = UUID.randomUUID().toString();
        tick = new Date();
    }

    public Date getTick() {
        return tick;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("tick", tick).toString();
    }

    @Id
    private String id;

    private Date tick;

}

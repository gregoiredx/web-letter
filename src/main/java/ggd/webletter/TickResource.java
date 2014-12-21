package ggd.webletter;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Named
@Resource
@Path("/tick")
public class TickResource {

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String tick(){
        Tick newTick = new Tick();
        entityManager.persist(newTick);
        entityManager.flush();
        entityManager.clear();
        List<Tick> ticks = entityManager.createQuery("from " + Tick.class.getName(), Tick.class).getResultList();
        String response = "ticks:\n";
        for (Tick tick : ticks) {
            response += tick + "\n";
        }
        return response;
    }

}

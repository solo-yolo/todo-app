package io.github.soloyolo;


import com.codahale.metrics.annotation.Metered;
import java.util.Collection;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class TasksResource {

    private final TasksDao dao;

    @GET
    @Metered
    public Collection<Task> getAll() {
        return dao.getAll();
    }

    @GET
    @Path("/{id}")
    @Metered
    public Task getOne(@PathParam("id") long id) {
        return dao.findById(id);
    }

    @POST
    @Metered
    public Task create(Task input) {
        return dao.create(input.getName());
    }

    @POST
    @Path("/{id}")
    @Metered
    public Task update(@PathParam("id") long id, Task input) {
        return dao.changeState(id, input.isDone());
    }

    @DELETE
    @Path("/{id}")
    @Metered
    public Task delete(@PathParam("id") long id) {
        return dao.remove(id);
    }

}

package io.github.soloyolo;

import com.codahale.metrics.annotation.Timed;
import java.util.Collection;

public interface TasksDao {

    @Timed
    Task create(String name);

    @Timed
    Task findById(Long id);

    @Timed
    Task changeState(Long id, boolean done);

    @Timed
    Collection<Task> getAll();

    @Timed
    Task remove(Long id);
}

package io.github.soloyolo;

import static io.github.soloyolo.TaskNotFoundException.notFound;

import com.codahale.metrics.annotation.Timed;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTasksDao implements TasksDao {

    private final Map<Long, Task> store = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Timed
    @Override
    public Task create(final String name) {
        final Task task = Task.builder()
                .id(counter.incrementAndGet())
                .name(name)
                .createdAt(new Date())
                .done(false)
                .build();
        store.put(task.getId(), task);
        return task;
    }

    @Timed
    @Override
    public Task findById(final Long id) {
        return Optional.ofNullable(store.get(id)).orElseThrow(() -> notFound(id));
    }

    @Timed
    @Override
    public Task changeState(final Long id, boolean done) {
        final Task task = findById(id);
        task.setDone(done);
        return task;
    }

    @Timed
    @Override
    public Collection<Task> getAll() {
        return store.values();
    }

    @Timed
    @Override
    public Task remove(final Long id) {
        if (store.containsKey(id)) {
            return store.remove(id);
        } else {
            throw notFound(id);
        }
    }
}

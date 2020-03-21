package io.github.soloyolo;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }

    public static TaskNotFoundException notFound(final Long id) {
        return new TaskNotFoundException(String.format("task with id '%d' not found", id));
    }
}

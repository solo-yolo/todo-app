package io.github.soloyolo;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class AppExceptionMapper implements ExceptionMapper<TaskNotFoundException> {

    @Override
    public Response toResponse(TaskNotFoundException exception) {
        return Response.status(Status.NOT_FOUND)
                .entity(new Error(Status.NOT_FOUND.getStatusCode(), exception.getMessage()))
                .build();
    }
}

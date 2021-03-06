package com.tw.api.exception;

import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        System.out.println(Exceptions.getStackTraceAsString(ex));
        return Response.status(500).entity(Exceptions.getStackTraceAsString(ex))
                .build();
    }
}

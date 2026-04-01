package com.engineeringcc.productmanagement.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mediator {

    private final Map<Class<? extends Request<?>>, RequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        requestHandlerMap = requestHandlers
                .stream()
                .collect(
                        Collectors.toMap(
                                RequestHandler::getRequestType,
                                Function.identity()
                        )
                );
    }

    public <R> R dispatch(Request<R> request) {
        RequestHandler<?, ?> rawHandler = requestHandlerMap.get(request.getClass());

        if (rawHandler == null) {
            throw new RuntimeException("No handler found for request type " + request.getClass());
        }

        if (!rawHandler.getRequestType().isAssignableFrom(request.getClass())) {
            throw new IllegalStateException("Handler/request type mismatch for request type " + request.getClass());
        }

        return invoke(rawHandler, request);
    }

    @SuppressWarnings("unchecked")
    private static <R, T extends Request<R>> R invoke(RequestHandler<?, ?> rawHandler, Request<R> request) {
        RequestHandler<T, R> handler = (RequestHandler<T, R>) rawHandler;
        return handler.handle((T) request);
    }
}

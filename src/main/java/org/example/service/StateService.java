package org.example.service;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.quarkus.panache.common.Page;
import org.example.entity.State;
import org.example.pages.PageRequest;
import org.example.repository.StateRepository;

@ApplicationScoped
public class StateService {

    @Inject
    StateRepository stateRepository;

    public Long count() {
        if(stateRepository.count() == 0)
            throw new WebApplicationException("States not found!", Response.Status.NOT_FOUND);

        return stateRepository.count();
    }

    public Response getAllPaged(PageRequest pageRequest) {
        if(stateRepository.findAll().count() == 0)
            throw new WebApplicationException("States not found!", Response.Status.NOT_FOUND);

        return Response
                .ok(stateRepository.findAll().page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize())).list())
                .build();
    }

    public Response persist(State state, UriInfo uriInfo) {
        stateRepository.persist(state);

        URI uri = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", state.getId()).build();
        return Response.created(uri).build();
    }

    public Response update(Long id, State state) {
        State updateState = stateRepository.findById(id);

        if (stateRepository.findById(id) == null)
            throw new WebApplicationException("State not found!", Response.Status.NOT_FOUND);

        updateState.setName(state.getName());
        updateState.setRegion(state.getRegion());

        return Response.ok(updateState).build();
    }

    public Response delete(Long id) {
        if (stateRepository.findById(id) == null)
            throw new WebApplicationException("State not found!", Response.Status.NOT_FOUND);

        stateRepository.deleteById(id);
        return Response.noContent().build();
    }
}

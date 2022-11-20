package org.example.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.example.entity.State;

@ApplicationScoped
public class StateRepository implements PanacheRepository<State>{

}

package org.example.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.example.entity.City;

@ApplicationScoped
public class CityRepository implements PanacheRepository<City>{

}

package com.jaygibran.pokedex.repository;

import com.jaygibran.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String> {
}

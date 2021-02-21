package com.jaygibran.pokedex.controller;

import com.jaygibran.pokedex.model.Pokemon;
import com.jaygibran.pokedex.model.PokemonEvent;
import com.jaygibran.pokedex.repository.PokedexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {

    private PokedexRepository pokedexRepository;

    public PokemonController(PokedexRepository pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }

    @GetMapping
    public Flux<Pokemon> getAllPokemons(){
        return this.pokedexRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getAllPokemons(@PathVariable String id){
        return this.pokedexRepository
                .findById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon){
        return this.pokedexRepository.save(pokemon);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable String id, @RequestBody  Pokemon pokemon){
        return pokedexRepository.findById(id).flatMap(existingPokemon -> {
            existingPokemon.setName(pokemon.getName());
            existingPokemon.setCategory(pokemon.getCategory());
            existingPokemon.setSkill(pokemon.getSkill());
            existingPokemon.setWeight(pokemon.getWeight());
            return pokedexRepository.save(existingPokemon);
        }).map(updatedPokemon -> ResponseEntity.ok(updatedPokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable String id){
        return this.pokedexRepository
                .deleteById(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllPokemons(){
        return this.pokedexRepository.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents(){
        return Flux.interval(Duration.ofSeconds(5)).map(val -> new PokemonEvent(val, "Evento de pokemon"));
    }
}

package com.jaygibran.pokedex;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestFlux {

    @Test
    public void testFlux1(){
        Flux.empty();
    }

    @Test
    public void testFlux2(){
        Flux<String> flux =  Flux.just("Pokedex");
        flux.subscribe(System.out::println);
    }

    @Test
    public void testMono3(){
        Flux<String> flux = Flux.just("Sharizard", "Blastoise", "Picachu");
        flux.subscribe(System.out::println);
    }

    @Test
    public void testMono4(){
        Mono<String> mono = Mono.error(new RuntimeException("Ocorreu uma excecao"));
    }
}

package com.example.reactive.webflux;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveMonoTest {
	
	@Test
	public void testMono() {
		Mono<String> monoResult = Mono.just("Mono Test").log();
		monoResult.subscribe(System.out::print);
	}
	
	@Test
	public void testMonoError() {
		Mono<?> monoResult = Mono.just("Mono Test").then(Mono.error(new RuntimeException("Test Error"))).log();
		monoResult.subscribe(System.out::print, e->System.out.println(e.getMessage()));
	}
	
	@Test
	public void testFlux() {
		Flux<String> fluxResult = Flux.just("Spring","Angular", "Postgresql").log();
		fluxResult.subscribe(System.out::print);
	}
	
	@Test
	public void testFluxConcat() {
		Flux<String> fluxResult = Flux.just("Spring","Angular", "Postgresql")
				.concatWithValues("AWS")
				.log();
		fluxResult.subscribe(System.out::print);
	}
	
	@Test
	public void testFluxError() {
		Flux<String> fluxResult = Flux.just("Spring","Angular", "Postgresql")
				.concatWith(Flux.error(new RuntimeException("Test Flux Error")))
				.log();
		fluxResult.subscribe(System.out::print, e -> System.out.println(e.getMessage()));
	}

}

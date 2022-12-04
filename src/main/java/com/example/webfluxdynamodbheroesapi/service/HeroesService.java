package com.example.webfluxdynamodbheroesapi.service;

import com.example.webfluxdynamodbheroesapi.document.Heroes;
import com.example.webfluxdynamodbheroesapi.repository.HeroesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class HeroesService {

	private final HeroesRepository heroesRepository;

	public Flux<Heroes> findAll() {
		return Flux.fromIterable(heroesRepository.findAll());
	}

	public Mono<Heroes> findByIdHero(String id) {
		return Mono.justOrEmpty(heroesRepository.findById(id));
	}

	public Mono<Heroes> save(Heroes heroes) {
		return Mono.justOrEmpty(heroesRepository.save(heroes));
	}

	public Mono<Boolean> deletebyIDHero(String id) {
		heroesRepository.deleteById(id);
		return Mono.just(true);
	}

}


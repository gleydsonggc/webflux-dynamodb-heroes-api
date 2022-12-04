package com.example.webfluxdynamodbheroesapi.controller;

import com.example.webfluxdynamodbheroesapi.document.Heroes;
import com.example.webfluxdynamodbheroesapi.service.HeroesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static com.example.webfluxdynamodbheroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(HEROES_ENDPOINT_LOCAL)
public class HeroesController {

	private final HeroesService heroesService;

	@GetMapping
	public Flux<Heroes> getAllItems() {
		log.info("requesting the list off all heroes");
		return heroesService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Heroes>> findByIdHero(@Valid @PathVariable String id) {
		log.info("Requesting the hero with id {}", id);
		return heroesService.findByIdHero(id)
				.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Heroes> createHero(@Valid @RequestBody Heroes heroes) {
		log.info("A new Hero was Created");
		return heroesService.save(heroes);
	}

	@DeleteMapping("/{id}")
	public Mono<HttpStatus> deletebyIDHero(@PathVariable String id) {
		heroesService.deletebyIDHero(id);
		log.info("Deleting the hero with id {}", id);
		return Mono.just(HttpStatus.NO_CONTENT);
	}

}

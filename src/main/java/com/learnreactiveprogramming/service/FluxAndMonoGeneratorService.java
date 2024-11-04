package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .log();
    }

    public Flux<String> namesFlux_map(int strLength) {
        //length greater than 3
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .map(s -> s.length() + "-" + s)
                .log();
    }

    public Flux<String> namesFlux_flatmap(int strLength) {
        //length greater than 3
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> namesFlux_flatmap_async(int strLength) {
        //length greater than 3
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> namesFlux_conactmap(int strLength) {
        //length greater than 3
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .concatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> namesFlux_transform(int strLength) {
        Function<Flux<String>, Flux<String>> filterMap = name ->
                name.map(String::toUpperCase)
                        .filter(s -> s.length() > strLength);

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> namesFlux_switchIfEmpty(int strLength) {
        Function<Flux<String>, Flux<String>> filterMap = name ->
                name.map(String::toUpperCase)
                        .filter(s -> s.length() > strLength);
        var defaultFlux = Flux.just("default")
                .transform(filterMap);

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Flux<String> splitString(String name) {
        return Flux.fromArray(name.split(""));
    }

    public Flux<String> splitStringWithDelay(String name) {
//        var delay = new Random().nextInt(1000);
        var delay = 1000;

        return Flux.fromArray(name.split(""))
                .delayElements(Duration.ofMillis(delay));
    }

    public Mono<String> namesMono_map_filter(int stringLength) {
        var nameMono = Mono.just("alex");

        return nameMono
                .map(String::toUpperCase)
                .filter(e -> e.length() > stringLength)
                .defaultIfEmpty("default");
    }

    public Mono<String> namesMono_map_filter_switchIfEmpty(int stringLength) {
        var nameMono = Mono.just("alex");
        var defaultMono = Mono.just("default")
                .map(String::toUpperCase);

        return nameMono
                .map(String::toUpperCase)
                .filter(e -> e.length() > stringLength)
                .switchIfEmpty(defaultMono);
    }

    public Mono<List<String>> namesMono_flatmap(int stringLength) {
        var nameMono = Mono.just("alex");

        return nameMono
                .map(String::toUpperCase)
                .filter(e -> e.length() > stringLength)
                .flatMap(this::splitStringMono)
                .log();
    }

    public Flux<String> namesMono_flatmapMany(int stringLength) {
        var nameMono = Mono.just("alex");

        return nameMono
                .map(String::toUpperCase)
                .filter(e -> e.length() > stringLength)
                .flatMapMany(this::splitString)
                .log();
    }

    private Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        var charList = List.of(charArray);

        return Mono.just(charList);
    }

    public Flux<String> namesFlux_immutability() {
        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase);

        return namesFlux;
    }

    public Mono<String> nameMono() {
        return Mono.just("alex")
                .log();
    }

    public Flux<String> explore_concat() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.concat(abcFlux, defFlux).log();
    }

    public Flux<String> explore_concatWith() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.concatWith(defFlux).log();
    }

    public Flux<String> explore_concatWith_mono() {
        var aMono = Mono.just("A");
        var bMono = Flux.just("B");

        return aMono.concatWith(bMono);
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> System.out.println("name is: " + name));
        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> System.out.println("Mono name is: " + name));
    }
}
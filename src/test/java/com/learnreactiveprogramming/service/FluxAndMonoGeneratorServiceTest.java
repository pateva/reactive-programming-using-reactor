package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

public class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService =
            new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        //then
        StepVerifier.create(namesFlux)
//                .expectNext("alex", "ben", "chloe")
//                .expectNextCount(3)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();

    }

    @Test
    void testMono() {
        //given

        //when
        var monoFlux = fluxAndMonoGeneratorService.nameMono();

        //then
        StepVerifier.create(monoFlux)
                .expectNext("alex")
                .expectComplete()
                .verify();
    }

    @Test
    void namesFlux_map() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_map(3);

        //then
        StepVerifier.create(namesFlux)
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFlux_immutability() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_immutability();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("alex", "ben", "chloe")
                .verifyComplete();
    }

    @Test
    void namesMono_map_filter() {
        //given

        //when
        var namesMono = fluxAndMonoGeneratorService.namesMono_map_filter(10);

        //then
        StepVerifier.create(namesMono)
                .expectComplete()
                .verify();
    }

    @Test
    void namesFlux_flatmap() {
        //given
        int strLenght = 3;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_flatmap(3);

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();

    }

    @Test
    void splitStringWithDelay() {
        //given
        int strLenght = 3;

        //when
        var namesFluxAsync = fluxAndMonoGeneratorService.namesFlux_flatmap_async(3);

        //then
        StepVerifier.create(namesFluxAsync)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesFlux_conactmap() {
        //given
        int strLenght = 3;

        //when
        var namesFluxAsync = fluxAndMonoGeneratorService.namesFlux_conactmap(3);

        //then
        StepVerifier.create(namesFluxAsync)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesFlux_flatmap_async() {
        //given
        int strLenght = 3;

        //when
        var namesFluxAsync = fluxAndMonoGeneratorService.namesFlux_flatmap_async(3);

        //then
        StepVerifier.create(namesFluxAsync)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesMono_flatmap() {
        //given
        int strLen = 3;

        //when
        var value = fluxAndMonoGeneratorService.namesMono_flatmap(strLen);

        //then
        StepVerifier.create(value)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void namesMono_flatmapMany() {
        //given
        int strLen = 3;

        //when
        var value = fluxAndMonoGeneratorService.namesMono_flatmapMany(strLen);

        //then
        StepVerifier.create(value)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform() {
        //given
        int strLen = 3;

        //when
        var value = fluxAndMonoGeneratorService.namesFlux_transform(strLen);

        //then
        StepVerifier.create(value)
                .expectNext("ALEX", "CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform_1() {
        //given
        int strLen = 6;

        //when
        var value = fluxAndMonoGeneratorService.namesFlux_transform(strLen);

        //then
        StepVerifier.create(value)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesFlux_switchIfEmpty() {
        //given
        int strLen = 6;

        //when
        var value = fluxAndMonoGeneratorService.namesFlux_switchIfEmpty(strLen);

        //then
        StepVerifier.create(value)
                .expectNext("DEFAULT")
                .verifyComplete();
    }

    @Test
    void testNamesMono_map_filter() {
        //given
        int strLen = 4;

        //when
        var value = fluxAndMonoGeneratorService.namesMono_map_filter(strLen);

        //then
        StepVerifier.create(value)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void testNamesMono_map_switchIfEmpty() {
        //given
        int strLen = 4;

        //when
        var value = fluxAndMonoGeneratorService.namesMono_map_filter_switchIfEmpty(strLen);

        //then
        StepVerifier.create(value)
                .expectNext("DEFAULT")
                .verifyComplete();
    }

    @Test
    void explore_concat() {
        //given

        //when
        var concatFlux = fluxAndMonoGeneratorService.explore_concat();

        //then
        StepVerifier.create(concatFlux)
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void explore_concatWith() {
        //given

        //when
        var concatFlux = fluxAndMonoGeneratorService.explore_concatWith();

        //then
        StepVerifier.create(concatFlux)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void explore_concatWith_mono() {
        //given

        //when
        var concatFlux = fluxAndMonoGeneratorService.explore_concatWith_mono();

        //then
        StepVerifier.create(concatFlux)
                .expectNext("A", "B")
                .verifyComplete();
    }
}

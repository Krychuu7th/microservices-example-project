package com.example.streamservice.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxMonoServiceTest {

    FluxMonoService fluxMonoService = new FluxMonoService();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxMonoService.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFlux = fluxMonoService.fruitsFluxMap();

        StepVerifier.create(fruitsFlux)
                .expectNext("MANGO", "ORANGE", "APPLE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFlux = fluxMonoService.fruitsFluxFilter(5);

        StepVerifier.create(fruitsFlux)
                .expectNext("Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFlux = fluxMonoService.fruitsFluxFilterMap(5);

        StepVerifier.create(fruitsFlux)
                .expectNext("ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFlux = fluxMonoService.fruitsFluxFlatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(16)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsFlux = fluxMonoService.fruitsFluxFlatMapAsync();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(16)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFlux = fluxMonoService.fruitsFluxConcatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(16)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitsFlux = fluxMonoService.fruitsFluxTransform(5);

        StepVerifier.create(fruitsFlux)
                .expectNext("Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitsFlux = fluxMonoService.fruitsFluxTransformDefaultIfEmpty(999);

        StepVerifier.create(fruitsFlux)
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFlux = fluxMonoService.fruitsFluxTransformSwitchIfEmpty(999);

        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple", "Watermelon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitsFlux = fluxMonoService.fruitsFluxConcat();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Apple", "Potato", "Carrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFlux = fluxMonoService.fruitsFluxConcatWith();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Apple", "Potato", "Carrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFlux = fluxMonoService.fruitsFluxMerge();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Potato", "Orange", "Carrot", "Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFlux = fluxMonoService.fruitsFluxMergeWith();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Potato", "Orange", "Carrot", "Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitsFlux = fluxMonoService.fruitsFluxMergeWithSequential();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Apple", "Potato", "Carrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFlux = fluxMonoService.fruitsFluxZip();

        StepVerifier.create(fruitsFlux)
                .expectNext("MangoPotato", "OrangeCarrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFlux = fluxMonoService.fruitsFluxZipWith();

        StepVerifier.create(fruitsFlux)
                .expectNext("MangoPotato", "OrangeCarrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFlux = fluxMonoService.fruitsFluxZipTuple();

        StepVerifier.create(fruitsFlux)
                .expectNext("MangoPotatoTomato", "OrangeCarrotLeak")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        var fruitsFlux = fluxMonoService.fruitsFluxFilterDoOn(4);

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFlux = fluxMonoService.fruitsFluxOnErrorReturn();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFlux = fluxMonoService.fruitsFluxOnErrorContinue();

        StepVerifier.create(fruitsFlux)
                .expectNext("MANGO", "APPLE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
        var fruitsFlux = fluxMonoService.fruitsFluxOnErrorMap();

        StepVerifier.create(fruitsFlux)
                .expectNext("MANGO")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxOnError() {
        var fruitsFlux = fluxMonoService.fruitsFluxOnError();

        StepVerifier.create(fruitsFlux)
                .expectNext("MANGO")
                .expectError(RuntimeException.class
                )
                .verify();
    }

    /*MONO*/

    @Test
    void fruitMono() {
        var fruitsMono = fluxMonoService.fruitMono();

        StepVerifier.create(fruitsMono)
                .expectNext("Mango")
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {
        var fruitsMono = fluxMonoService.fruitMonoFlatMap();

        StepVerifier.create(fruitsMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany() {
        var fruitsFlux = fluxMonoService.fruitMonoFlatMapMany();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {
        var fruitsFlux = fluxMonoService.fruitsMonoConcatWith();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Potato")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var fruitsMono = fluxMonoService.fruitsMonoZipWith();

        StepVerifier.create(fruitsMono)
                .expectNext("MangoPotato")
                .verifyComplete();
    }
}
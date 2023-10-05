package com.example.streamservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxMonoService {

    /*FLUX*/

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(getFruits()).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(getFruits())
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(getFruits())
                .filter(s -> s.length() > number);
    }

    public Flux<String> fruitsFluxFilterMap(int number) {
        return Flux.fromIterable(getFruits())
                .filter(s -> s.length() > number)
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(getFruits())
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(getFruits())
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(3000)))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(getFruits())
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(getFruits())
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(getFruits())
                .transform(filterData)
                .defaultIfEmpty("Banana")
                .log();
    }

    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(getFruits())
                .transform(filterData)
                .switchIfEmpty(Flux.just("Pineapple", "Watermelon"))
                .log();
    }

    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.fromIterable(getFruits());
        var vegetables = Flux.just("Potato", "Carrot");

        return Flux.concat(fruits, vegetables);
    }

    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.fromIterable(getFruits());
        var vegetables = Flux.just("Potato", "Carrot");

        return fruits.concatWith(vegetables);
    }

    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.fromIterable(getFruits())
                .delayElements(Duration.ofMillis(60));
        var vegetables = Flux.just("Potato", "Carrot")
                .delayElements(Duration.ofMillis(75));

        return Flux.merge(fruits, vegetables).log();
    }

    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.fromIterable(getFruits())
                .delayElements(Duration.ofMillis(60));
        var vegetables = Flux.just("Potato", "Carrot")
                .delayElements(Duration.ofMillis(75));

        return fruits.mergeWith(vegetables).log();
    }

    public Flux<String> fruitsFluxMergeWithSequential() {
        var fruits = Flux.fromIterable(getFruits())
                .delayElements(Duration.ofMillis(60));
        var vegetables = Flux.just("Potato", "Carrot")
                .delayElements(Duration.ofMillis(75));

        return Flux.mergeSequential(fruits, vegetables).log();
    }

    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.fromIterable(getFruits());
        var vegetables = Flux.just("Potato", "Carrot");

        return Flux.zip(fruits, vegetables, (first, second) -> first + second).log();
    }

    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.fromIterable(getFruits());
        var vegetables = Flux.just("Potato", "Carrot");

        return fruits.zipWith(vegetables, (first, second) -> first + second).log();
    }

    public Flux<String> fruitsFluxZipTuple() {
        var fruits = Flux.fromIterable(getFruits());
        var vegetables = Flux.just("Potato", "Carrot");
        var moveVegetables = Flux.just("Tomato", "Leak");

        return Flux.zip(fruits, vegetables, moveVegetables).map(
                objects -> objects.getT1() + objects.getT2() + objects.getT3()
        ).log();
    }

    public Flux<String> fruitsFluxFilterDoOn(int number) {
        return Flux.fromIterable(getFruits())
                .filter(s -> s.length() > number)
                .doOnNext(s -> {
                    System.out.println("s = " + s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription = " + subscription);
                })
                .doOnComplete(() -> System.out.println("Completed!"))
                .log();
    }

    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux.just("Mango", "Orange")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .onErrorReturn("Apple");
    }

    /*MONO*/

    public Mono<String> fruitMono() {
        return Mono.just("Mango").log();
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsMonoConcatWith() {
        var fruit = Mono.just("Mango");
        var vegetable = Mono.just("Potato");

        return fruit.concatWith(vegetable);
    }

    public Mono<String> fruitsMonoZipWith() {
        var fruit = Mono.just("Mango");
        var vegetable = Mono.just("Potato");

        return fruit.zipWith(vegetable, (first, second) -> first + second).log();
    }

    public static void main(String[] args) {
        FluxMonoService fluxMonoService = new FluxMonoService();

        fluxMonoService.fruitsFlux().subscribe(s -> System.out.println("Flux s = " + s));

        fluxMonoService.fruitMono().subscribe(s -> System.out.println("Mono s = " + s));

        for (int i = 0; i < 10; i++) {
            System.out.println(new Random().nextInt(1000));
        }
    }

    private static List<String> getFruits() {
        return List.of("Mango", "Orange", "Apple");
    }
}

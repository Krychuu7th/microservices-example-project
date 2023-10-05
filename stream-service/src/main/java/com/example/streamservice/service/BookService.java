package com.example.streamservice.service;

import com.example.streamservice.domain.Book;
import com.example.streamservice.domain.Review;
import com.example.streamservice.exception.BookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;

    public Flux<Book> getBooks() {
        var allBooks = bookInfoService.getBookInfoList();
        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: " + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .log();
    }

    public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBookInfoList();
        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: " + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retry(3)
                .log();
    }

    public Flux<Book> getBooksRetryWhen() {
        var allBooks = bookInfoService.getBookInfoList();
        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: " + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retryWhen(getRetryBackoffSpec())
                .log();
    }

    public Mono<Book> getBookById(long bookId) {
        var book = bookInfoService.getBookInfo(bookId);
        var reviews = reviewService.getReviews(bookId).collectList();

        return book.zipWith(reviews, Book::new).log();
    }

    private static RetryBackoffSpec getRetryBackoffSpec() {
        return Retry.backoff(3, Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())
                );
    }

}

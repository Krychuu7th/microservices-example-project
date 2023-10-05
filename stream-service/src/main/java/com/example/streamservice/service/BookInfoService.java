package com.example.streamservice.service;

import com.example.streamservice.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;

public class BookInfoService {

    public Flux<BookInfo> getBookInfoList() {
        return Flux.fromIterable(List.of(
                new BookInfo(1, "Book One", "Author One", "12121212"),
                new BookInfo(2, "Book Two", "Author Two", "42342342"),
                new BookInfo(3, "Book Three", "Author Three", "98989898")
        ));
    }

    public Mono<BookInfo> getBookInfo(long bookId) {
        var bookInfoList = List.of(
                new BookInfo(1, "Book One", "Author One", "12121212"),
                new BookInfo(2, "Book Two", "Author Two", "42342342"),
                new BookInfo(3, "Book Three", "Author Three", "98989898")
        );

        return Mono.just(bookInfoList.stream()
                .filter(bookInfo -> bookInfo.getId() == bookId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new));
    }
}

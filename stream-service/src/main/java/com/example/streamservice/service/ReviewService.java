package com.example.streamservice.service;

import com.example.streamservice.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviews(long bookId) {
        var reviewList = List.of(
                new Review(1, bookId, 9.5, "Good book"),
                new Review(2, bookId, 8.4, "Worth reading")
        );

        return Flux.fromIterable(reviewList);
    }
}

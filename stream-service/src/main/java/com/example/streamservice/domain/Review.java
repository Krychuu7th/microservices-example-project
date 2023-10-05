package com.example.streamservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private long id;
    private long bookId;
    private double ratings;
    private String comment;
}

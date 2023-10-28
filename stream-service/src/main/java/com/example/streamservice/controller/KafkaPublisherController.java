package com.example.streamservice.controller;

import com.example.streamservice.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stream/kafka")
public class KafkaPublisherController {

    private final KafkaTemplate<String, Book> kafkaTemplate;

    private static final String TOPIC = "NewTopic";

    @PostMapping("/publish")
    public String publishBook(@RequestBody Book book) {
        kafkaTemplate.send(TOPIC, book);
        return "Published Book Successfully!";
    }
}

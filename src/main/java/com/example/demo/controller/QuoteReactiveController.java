package com.example.demo.controller;

import java.time.Duration;

import com.example.demo.models.Quote;
import com.example.demo.repository.QuoteMongoReactiveRepository;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class QuoteReactiveController {
    private static final int DELAY_PER_ITEMS_MS = 100;

    private final QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    public QuoteReactiveController(QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @GetMapping(value = "/quotes-reactive")
    public Flux<Quote> getQuoteFlux() {
        System.out.println("getting reactive data");
        return quoteMongoReactiveRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEMS_MS));
    }

    @GetMapping(value = "/quotes-reactive-paged")
    public Flux<Quote> getQuoFlux(final @RequestParam(name = "page") int page,
            final @RequestParam(name = "size") int size) {
        System.out.println("getting reactive paginated data");
        return quoteMongoReactiveRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEMS_MS));
    }
}

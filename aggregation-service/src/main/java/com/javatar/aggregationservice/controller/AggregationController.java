package com.javatar.aggregationservice.controller;


import com.javatar.aggregationservice.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aggregate")
public class AggregationController {

    private final WebClient.Builder webClientBuilder;

    public AggregationController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/{reviewid}")
    public AggregatedReview aggregate(@PathVariable Integer reviewid) {
        List<Genre> genres = webClientBuilder.build()
                .get()
                .uri("http://genres-service/api/v1/genres/" + reviewid)
                .retrieve()
                .bodyToFlux(Genre.class)
                .collectList()
                .block();

        List<Label> labels = webClientBuilder.build()
                .get()
                .uri("http://labels-service/api/v1/labels/" + reviewid)
                .retrieve()
                .bodyToFlux(Label.class)
                .collectList()
                .block();

        List<Year> years = webClientBuilder.build()
                .get()
                .uri("http://years-service/api/v1/years/" + reviewid)
                .retrieve()
                .bodyToFlux(Year.class)
                .collectList()
                .block();

        AggregatedReview aggregated = new AggregatedReview();
        aggregated.setReviewid(reviewid);
        aggregated.setGenres(genres.stream().map(Genre::getGenre).toList());
        aggregated.setLabels(labels.stream().map(Label::getLabel).toList());
        aggregated.setYears(years.stream().map(Year::getYear).toList());

        return aggregated;
    }
}

package com.javatar.aggregationservice.controller;

import com.javatar.aggregationservice.model.*;
import com.javatar.aggregationservice.service.BlockSenderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/aggregate")
public class AggregationController {

    private final WebClient.Builder webClientBuilder;
    private final BlockSenderService blockSenderService;

    public AggregationController(WebClient.Builder webClientBuilder, BlockSenderService blockSenderService) {
        this.webClientBuilder = webClientBuilder;
        this.blockSenderService = blockSenderService;
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

        // ساخت بلاک و ارسال به Raft
        Block block = new Block();
        block.setId(UUID.randomUUID().toString());
        block.setTimestamp(System.currentTimeMillis());
        block.setData(
                List.of(
                        "Genres: " + aggregated.getGenres(),
                        "Labels: " + aggregated.getLabels(),
                        "Years: " + aggregated.getYears()
                )
        );
        blockSenderService.sendBlock(block);

        return aggregated;
    }
}

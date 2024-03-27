package dev.zooty.client.runners;

import dev.zooty.client.services.RandomService;
import dev.zooty.protobuf.NumberRange;
import dev.zooty.protobuf.RandomNumbersRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RandomRunner implements CommandLineRunner {
    private final RandomService randomService;

    public RandomRunner(RandomService randomService) {
        this.randomService = randomService;
    }

    @Override
    public void run(String... args) {
        randomService.getRandomNumbers(RandomNumbersRequest
                .newBuilder()
                .setAmount(66)
                .setRange(NumberRange
                        .newBuilder()
                        .setMaximum(256)
                        .setMinimum(0)
                        .build())
                .build())
                .forEachRemaining(randomNumberResponse -> log.info("Got random number: {}", randomNumberResponse.getRandomNumber()));
    }
}

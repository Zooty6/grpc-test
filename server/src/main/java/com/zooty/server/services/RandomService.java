package com.zooty.server.services;

import com.zooty.protobuf.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@GrpcService
public class RandomService extends RandomServiceGrpc.RandomServiceImplBase {
    private static final Random random = new Random();

    @Override
    public void getRandomNumber(RandomNumberRequest request, StreamObserver<RandomNumberResponse> responseObserver) {
        int newRandomNumber = getNewRandomNumber(Optional.ofNullable(request.hasRange() ? request.getRange() : null));
        responseObserver.onNext(RandomNumberResponse
                .newBuilder()
                .setRandomNumber(newRandomNumber)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getRandomNumbers(RandomNumbersRequest request, StreamObserver<RandomNumberResponse> responseObserver) {
        IntStream.range(0, request.getAmount())
                .mapToObj(value -> Optional.ofNullable(request.hasRange() ? request.getRange() : null))
                .map(this::getNewRandomNumber)
                .map(randomNumber -> RandomNumberResponse
                        .newBuilder()
                        .setRandomNumber(randomNumber)
                        .build())
                .forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }

    private int getNewRandomNumber(Optional<NumberRange> numberRange) {
        return numberRange
                .map(range -> random.nextInt(range.getMinimum(), range.getMaximum()))
                .orElse(random.nextInt());
    }
}


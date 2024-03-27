package dev.zooty.client.services;

import dev.zooty.protobuf.RandomNumberRequest;
import dev.zooty.protobuf.RandomNumberResponse;
import dev.zooty.protobuf.RandomNumbersRequest;
import dev.zooty.protobuf.RandomServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class RandomService {
    @GrpcClient("randomServiceClient")
    private RandomServiceGrpc.RandomServiceBlockingStub stub;

    public RandomNumberResponse getRandomNumber(RandomNumberRequest request) {
        return stub.getRandomNumber(request);
    }

    public Iterator<RandomNumberResponse> getRandomNumbers(RandomNumbersRequest request) {
        return stub.getRandomNumbers(request);
    }
}

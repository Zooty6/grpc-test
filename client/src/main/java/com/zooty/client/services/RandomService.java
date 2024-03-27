package com.zooty.client.services;

import com.zooty.protobuf.RandomNumberRequest;
import com.zooty.protobuf.RandomNumberResponse;
import com.zooty.protobuf.RandomNumbersRequest;
import com.zooty.protobuf.RandomServiceGrpc;
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

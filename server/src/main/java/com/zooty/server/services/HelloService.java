package com.zooty.server.services;

import com.zooty.protobuf.HelloWorldRequest;
import com.zooty.protobuf.HelloWorldResponse;
import com.zooty.protobuf.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class HelloService extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void sayHello(HelloWorldRequest request, StreamObserver<HelloWorldResponse> responseObserver) {
        log.info("Hello service was called with {}", request);
        responseObserver.onNext(HelloWorldResponse
                .newBuilder()
                .setGreeting("Hello " + request.getName())
                .build());
        responseObserver.onCompleted();
    }

}

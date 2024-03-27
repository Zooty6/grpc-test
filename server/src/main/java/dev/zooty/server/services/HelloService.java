package dev.zooty.server.services;

import dev.zooty.protobuf.HelloWorldRequest;
import dev.zooty.protobuf.HelloWorldResponse;
import dev.zooty.protobuf.HelloWorldServiceGrpc;
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

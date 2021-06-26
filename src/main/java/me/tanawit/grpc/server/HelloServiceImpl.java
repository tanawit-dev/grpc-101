package me.tanawit.grpc.server;

import io.grpc.stub.StreamObserver;
import me.tanawit.grpc.HelloRequest;
import me.tanawit.grpc.HelloResponse;
import me.tanawit.grpc.HelloServiceGrpc;

/**
 * @author Tanawit Aeabsakul
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
	@Override
	public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
		System.out.println("hello server was called");
		String greeting = String.format("Hello, %s %s", request.getFirstName(), request.getLastName());

		HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}

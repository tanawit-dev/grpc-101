package me.tanawit.grpc.greeting.client;

import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.tanawit.grpc.HelloRequest;
import me.tanawit.grpc.HelloResponse;
import me.tanawit.grpc.HelloServiceGrpc;

/**
 * @author Tanawit Aeabsakul
 */
public class GrpcClient {
	private final static Logger LOGGER = Logger.getLogger(GrpcClient.class.getName());

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

		HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

		HelloRequest request = HelloRequest.newBuilder().setFirstName("John").setLastName("Done").build();

		LOGGER.info("Send request");
		HelloResponse response = stub.hello(request);
		LOGGER.info("Receive response");

		LOGGER.info(String.format("response: %s", response));

		channel.shutdown();
	}
}

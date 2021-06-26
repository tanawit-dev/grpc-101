package me.tanawit.grpc.greeting.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @author Tanawit Aeabsakul
 */
public class GrpcServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(8080).addService(new HelloServiceImpl()).build();

		server.start();
		System.out.println("Server is starting");
		server.awaitTermination();
	}
}

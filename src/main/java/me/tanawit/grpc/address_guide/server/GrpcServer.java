package me.tanawit.grpc.address_guide.server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @author Tanawit Aeabsakul
 */
public class GrpcServer {
	private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());
	private final int port = 8080;
	private final Server server;

	public GrpcServer() {
		server = ServerBuilder.forPort(port).addService(new AddressGuideServiceImpl()).build();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		GrpcServer grpcServer = new GrpcServer();
		grpcServer.start();
		grpcServer.blockUtilShutdown();
	}

	private void blockUtilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	private void start() throws IOException {
		server.start();
		logger.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				GrpcServer.this.stop();
			} catch (InterruptedException e) {
				e.printStackTrace(System.err);
			}

			logger.info("Server shutdown");
		}));
	}

	private void stop() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}
}

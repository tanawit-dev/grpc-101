package me.tanawit.grpc.address_guide.client;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.tanawit.grpc.AddressBook;
import me.tanawit.grpc.AddressGuideGrpc;
import me.tanawit.grpc.Person;

/**
 * @author Tanawit Aeabsakul
 */
public class GrpcClient {
	private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());
	private final String host = "localhost";
	private final int port = 8080;
	private final ManagedChannel channel;
	private final AddressGuideGrpc.AddressGuideBlockingStub blockingStub;
	private final AddressGuideGrpc.AddressGuideStub asyncStub;

	public GrpcClient() {
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		blockingStub = AddressGuideGrpc.newBlockingStub(channel);
		asyncStub = AddressGuideGrpc.newStub(channel);
	}

	public static void main(String[] args) throws InterruptedException {
		GrpcClient grpcClient = new GrpcClient();
		Person.PhoneNumber phoneNumber = Person.PhoneNumber.newBuilder().setNumber("09000000000").build();
		Person john =
				Person.newBuilder().setName("John").setId(1).setEmail("john@mail.com").addPhoneNumbers(phoneNumber).build();
		AddressBook addressBook = grpcClient.getAddressBook(john);

		logger.info("address book: " + addressBook.getPeopleList());

		grpcClient.shutdown();
	}

	private AddressBook getAddressBook(Person person) {
		return blockingStub.getAddressBook(person);
	}

	private void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
}

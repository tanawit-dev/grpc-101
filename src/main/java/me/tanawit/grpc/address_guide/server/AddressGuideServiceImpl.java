package me.tanawit.grpc.address_guide.server;

import java.util.Map;

import io.grpc.stub.StreamObserver;
import me.tanawit.grpc.AddressBook;
import me.tanawit.grpc.AddressGuideGrpc;
import me.tanawit.grpc.Person;

/**
 * @author Tanawit Aeabsakul
 */
public class AddressGuideServiceImpl extends AddressGuideGrpc.AddressGuideImplBase {
	private final Map<Integer, Person> personMap;

	public AddressGuideServiceImpl() {
		personMap = Map.of(1, Person.newBuilder().setId(2).setName("Sarah").setEmail("sarah@mail.com").build());
	}

	@Override
	public void getAddressBook(Person request, StreamObserver<AddressBook> responseObserver) {
		responseObserver.onNext(getAddressBook(request.getId()));
		responseObserver.onCompleted();
	}

	private AddressBook getAddressBook(int id) {
		Person person = personMap.get(id);

		return AddressBook.newBuilder().addPeople(person).build();
	}
}

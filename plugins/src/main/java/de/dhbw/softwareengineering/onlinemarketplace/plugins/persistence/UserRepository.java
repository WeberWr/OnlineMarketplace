package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class UserRepository  implements IUserRepository {

	private final DataUserRepository dataUserRepository;

	@Autowired
	public UserRepository(final DataUserRepository dataUserRepository) {
		this.dataUserRepository = dataUserRepository;
	}

	@Override
	public List<User> findAllUsers() {
		return dataUserRepository.findAll();
	}

	@Override
	public User findUserWithId(UUID id) {
		var user = dataUserRepository.findById(id);
		if (user.isEmpty()){
			throw new RuntimeException("User with id " + id + " not found");
		}
		return user.get();
	}

	@Override
	public User create(User user) {
		return dataUserRepository.save(user);
	}

	@Override
	public void deleteById(UUID id) {
		dataUserRepository.deleteById(id);
	}
}

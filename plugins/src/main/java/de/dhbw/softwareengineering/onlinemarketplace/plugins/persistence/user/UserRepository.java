package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class UserRepository implements IUserRepository {
	private final DataUserRepository dataUserRepository;

	@Autowired
	public UserRepository(DataUserRepository dataUserRepository) {
		this.dataUserRepository = dataUserRepository;
	}

	@Override
	public Optional<User> getUserById(UUID id) {
		return dataUserRepository.findById(id);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return dataUserRepository.findUserByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return dataUserRepository.findAll();
	}

	@Override
	public void create(User user) {
		dataUserRepository.save(user);
	}

	@Override
	public void update(User user) {
		dataUserRepository.save(user);
	}

	@Override
	public void deleteUser(UUID id) {
		dataUserRepository.deleteById(id);
	}
}

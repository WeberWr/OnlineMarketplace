package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


//Muss das jeweilige Domain Repository implementieren
@Repository
public class UserRepository  implements IUserRepository {


	private final DataUserRepository dataUserRepository;
	@Autowired
	public UserRepository(final DataUserRepository dataUserRepository) {
		this.dataUserRepository = dataUserRepository;
	}

	@Override
	public List<User> findAllUsers() {
		dataUserRepository.findAll();
		return null;
	}

	@Override
	public User findUserWithId(UUID id) {
		dataUserRepository.findById(id);
		return null;
	}

	@Override
	public User create(User user) {
		dataUserRepository.save(user);
		return null;
	}

	@Override
	public boolean deleteById(UUID id) {
		dataUserRepository.deleteById(id);
		return false;
	}
}

package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence.user;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DataUserRepository extends MongoRepository<User, UUID> {
    @Query("{'email': ?0}")
    Optional<User> findUserByEmail(String email);
}

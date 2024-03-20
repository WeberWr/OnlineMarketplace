package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DataUserRepository extends MongoRepository<User, UUID> {
}

package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.onlinemarketplace.domain.entities.EntityExample;
@Repository
public interface springDataForBridge extends JpaRepository<EntityExample, String> {

}

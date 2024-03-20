package de.dhbw.softwareengineering.OnlineMarketplace.application.services1;


import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.onlinemarketplace.domain.repositories.BridgeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final IUserRepository repository;
    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers(){
        return repository.findAllUsers();
    };

    public User findUserWithId(UUID id){
        return repository.findUserWithId(id);
    };

    public User create(User user){
        return repository.create(user);

    };

    boolean deleteById(UUID id){
        return repository.deleteById(id);
    };
}

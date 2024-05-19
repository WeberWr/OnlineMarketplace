package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.validation.EmailValidator;
import org.apache.commons.lang3.Validate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public final class User {
    @Id
    private UUID id;
    private Name name;
    private String email;
    private String password;

    public User(Name name, String email, String password) {
        Validate.notNull(name);
        Validate.notBlank(email);
        Validate.isTrue(EmailValidator.getInstance().isValidEmail(email));
        Validate.notBlank(password);

        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID id() {
        return id;
    }

    public Name name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }
}
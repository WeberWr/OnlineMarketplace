package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.EmailValidator;
import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;
import org.apache.commons.lang3.Validate;

import java.util.Objects;
import java.util.UUID;

public final class User {
    private final UUID id;
    private final Name name;
    private final String email;
    private final String password;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "email=" + email + ", " +
                "password=" + password + ']';
    }

}
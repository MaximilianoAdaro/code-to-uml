package org.dissis.ex3;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Optional;

public class UserRegisterProgram {

    class UserController {
        private UserService service;

        public HttpResponse<User> createUser(HttpRequest request) {
            String body = "";// get content from request
            var registerRequest = new JsonRegistrationRequestDeserializer().deserialize(body);
            var user = service.register(registerRequest);
            return null; // build HttpResponse with serialized User
        }
    }

    class JsonRegistrationRequestDeserializer {
        public RegistrationRequest deserialize(String json) {
            return null;
        }
    }

    class UserService {

        private final UserRepository repository;

        public UserService(UserRepository repository) {
            this.repository = repository;
        }

        User register(RegistrationRequest user) {
            return repository.saveUser(user);
        }
    }

    interface UserRepository {

        Boolean existsUserWithEmail(String email);
        User saveUser(RegistrationRequest user);
        Optional<User> getUserByEmail(Long id);
        Optional<User> getUserByEmail(String email);
        Collection<User> getUsers();
    }

    class MongoUserRepository implements UserRepository {
        @Override
        public Boolean existsUserWithEmail(String email) {
            return null;
        }

        @Override
        public User saveUser(RegistrationRequest user) {
            return null;
        }

        @Override
        public Optional<User> getUserByEmail(Long id) {
            return Optional.empty();
        }

        @Override
        public Optional<User> getUserByEmail(String email) {
            return Optional.empty();
        }

        @Override
        public Collection<User> getUsers() {
            return null;
        }
    }

    class PostgresUserRepository implements UserRepository {
        @Override
        public Boolean existsUserWithEmail(String email) {
            return null;
        }

        @Override
        public User saveUser(RegistrationRequest user) {
            return null;
        }

        @Override
        public Optional<User> getUserByEmail(Long id) {
            return Optional.empty();
        }

        @Override
        public Optional<User> getUserByEmail(String email) {
            return Optional.empty();
        }

        @Override
        public Collection<User> getUsers() {
            return null;
        }
    }

    class InMemoryUserRepository implements UserRepository {
        @Override
        public Boolean existsUserWithEmail(String email) {
            return null;
        }

        @Override
        public User saveUser(RegistrationRequest user) {
            return null;
        }

        @Override
        public Optional<User> getUserByEmail(Long id) {
            return Optional.empty();
        }

        @Override
        public Optional<User> getUserByEmail(String email) {
            return Optional.empty();
        }

        @Override
        public Collection<User> getUsers() {
            return null;
        }
    }

    class ShadedPasswordUserRepository implements UserRepository {

        private UserRepository repository;

        /**
         Wraps a UserRepository and intercepts User queries to avoid exposings a User's password.
         */
        public ShadedPasswordUserRepository(UserRepository repository) {
            this.repository = repository;
        }

        @Override
        public Boolean existsUserWithEmail(String email) {
            return null;
        }

        @Override
        public User saveUser(RegistrationRequest user) {
            return null;
        }

        @Override
        public Optional<User> getUserByEmail(Long id) {
            return Optional.empty();
        }

        @Override
        public Optional<User> getUserByEmail(String email) {
            return Optional.empty();
        }

        @Override
        public Collection<User> getUsers() {
            return null;
        }
    }

    class User {

        private final String email;
        private final String password;
        private final Integer age;
        private final Long id;

        public User(String email, String password, Integer age, Long id) {
            this.email = email;
            this.password = password;
            this.age = age;
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public Integer getAge() {
            return age;
        }

        public Long getId() {
            return id;
        }
    }

    class RegistrationRequest {
        public final String email;
        public final String password;
        public final Integer age;

        public RegistrationRequest(String email, String password, Integer age) {
            this.email = email;
            this.password = password;
            this.age = age;
        }
    }
}

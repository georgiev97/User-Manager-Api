package com.usermanager.UserManagerApi.repository.user;

import com.usermanager.UserManagerApi.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    @Query("{ 'id' : ?0 }")
    User findOneById(String id);
}

package com.spring.projectplanner.repositories;

import com.spring.projectplanner.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    UserModel findByUsername(String username);
    UserModel getById(Long id);


}

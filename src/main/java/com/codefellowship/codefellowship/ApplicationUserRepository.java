package com.codefellowship.codefellowship;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUserModel,Integer> {
    public ApplicationUserModel findByUsername(String userName);
}

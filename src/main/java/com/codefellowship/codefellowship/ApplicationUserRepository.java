package com.codefellowship.codefellowship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserModel,Integer> {
    public ApplicationUserModel findByUsername(String userName);


    @Query(value = "SELECT * FROM  following f INNER JOIN application_user_model a on  a.id= f.user_id where user_id =?1", nativeQuery = true)
     public List<ApplicationUserModel> findFollowedUser(Integer user_id);
}

package com.codefellowship.codefellowship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserModel, Integer> {
    public ApplicationUserModel findByUsername(String userName);



    @Query(value = "SELECT * FROM  following f INNER JOIN application_user_model a on  a.id= f.user_id where user_id =?1", nativeQuery = true)
    public List<ApplicationUserModel> findFollowedUser(Integer user_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO following (user_id, follow_id) VALUES (?1,?2)", nativeQuery = true)
    public void insert(Integer user_id, Integer follow_id);
}

package com.codefellowship.codefellowship;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostReppsitory extends CrudRepository<PostModel,Integer> {
    public List<PostModel> findAllByApplicationUserModelId(Integer id);
    @Query(value = "SELECT * FROM  following f INNER JOIN post_model p on  p.id= f.follow_id where user_id =?1", nativeQuery = true)
    public List<PostModel> findFollowedUser(Integer user_id);

}

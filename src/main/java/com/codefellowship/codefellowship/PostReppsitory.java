package com.codefellowship.codefellowship;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostReppsitory extends CrudRepository<PostModel,Integer> {
    public List<PostModel> findAllByApplicationUserModelId(Integer id);


}

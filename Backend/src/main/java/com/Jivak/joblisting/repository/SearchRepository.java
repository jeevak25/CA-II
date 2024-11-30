package com.Jivak.joblisting.repository;

import com.Jivak.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {

    List<Post> findByText(String text);

}

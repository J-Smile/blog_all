package com.smile.repository;

import com.smile.domain.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface  BlogRepository  extends ElasticsearchRepository<Blog,String> {

    List<Blog> findByTitleLike(String title);
}

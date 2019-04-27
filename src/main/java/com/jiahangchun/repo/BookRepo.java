package com.jiahangchun.repo;

import com.jiahangchun.DO.BookDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends ElasticsearchRepository<BookDO, String> {
    // 根据author查询数据
    Page<BookDO> findByBookName(String bookName, Pageable pageable);


//    Page<BookDO> findAll();

//    // 自定义查询
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"author\": \"?0\"}}]}}")
//    List<Article> findByAuthorUsingCustomQuery(String author);

}

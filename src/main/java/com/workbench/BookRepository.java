package com.workbench;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by RdDvls on 1/4/17.
 */
public interface BookRepository extends CrudRepository <BookItem, Integer>{
    List<BookItem> findByReaders (Reader reader);
}

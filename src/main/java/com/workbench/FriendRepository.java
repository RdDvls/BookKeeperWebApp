package com.workbench;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by RdDvls on 1/10/17.
 */
public interface FriendRepository extends CrudRepository <Friend, Integer> {
    List<Friend> findByReaders (Reader reader);
}

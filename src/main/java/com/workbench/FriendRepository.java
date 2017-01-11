package com.workbench;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by RdDvls on 1/10/17.
 */
public interface FriendRepository extends CrudRepository <Friend, Integer> {
    Friend findByFriendName (String friendName);
}

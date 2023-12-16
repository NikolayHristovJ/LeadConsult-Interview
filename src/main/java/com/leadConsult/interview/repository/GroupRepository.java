package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Group;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends ListCrudRepository<Group,Long> {

}

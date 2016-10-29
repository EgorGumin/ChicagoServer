package com.chicago.dao;


import com.chicago.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel.
 * Date: 19.03.2016 0:00.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Long> {

//    List<Users> findByName(@Param("username") String name);
//
//    @Query(value = "select  u from Users u where u.username like concat('%', :substr,'%') " +
//            "or u.email like concat('%', :substr,'%')")
//    List<Users> findBySubstring(@Param("substr") String substr);

}

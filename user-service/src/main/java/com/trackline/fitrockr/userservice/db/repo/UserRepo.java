package com.trackline.fitrockr.userservice.db.repo;

import com.trackline.fitrockr.userservice.db.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Transactional
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
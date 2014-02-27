package com.tecktree.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConnectionRepository extends JpaRepository<UserConnection, UserConnectionIDs> {

}

package me.tbsten.mytwi.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import me.tbsten.mytwi.auth.model.User;

public interface UserRepository extends JpaRepository<User, String>{
}

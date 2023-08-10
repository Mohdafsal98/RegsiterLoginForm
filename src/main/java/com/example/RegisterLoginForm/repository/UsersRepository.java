package com.example.RegisterLoginForm.repository;

import com.example.RegisterLoginForm.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer>
{
    Optional<UsersModel> findByLoginAndPassword(String login, String password);

}

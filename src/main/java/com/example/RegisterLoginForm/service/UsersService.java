package com.example.RegisterLoginForm.service;

import com.example.RegisterLoginForm.model.UsersModel;
import com.example.RegisterLoginForm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsersService {


    @Autowired
    private UsersRepository usersRepository;

    public UsersModel registerUser(String login, String password, String email) {

        if (login != null && password != null) {
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            System.out.println(usersModel);
            return usersRepository.save(usersModel);
        } else {
            return null;
        }
    }


    public UsersModel authenticate(String login, String password) {

        return usersRepository.findByLoginAndPassword(login, password).orElse(null);

    }

    public UsersModel updateDetails(UsersModel usersModel) {
        UsersModel updateDetail = usersRepository.findById(usersModel.getId()).orElse(null);
        if (updateDetail != null) {

            updateDetail.setLogin(usersModel.getLogin());
            updateDetail.setEmail(usersModel.getEmail());
            usersRepository.save(updateDetail);
            return updateDetail;
        }
    return null;

    }
}






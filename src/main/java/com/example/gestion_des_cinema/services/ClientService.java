package com.example.gestion_des_cinema.services;

import com.example.gestion_des_cinema.dao.entities.Client;
import com.example.gestion_des_cinema.dao.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements  ClientManager{

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override

    public Client addClient(Client client){
        clientRepository.save(client);
        return client;
    }

    @Override
    public boolean deleteCeator(long id) {

        Optional<Client> existingCreator = clientRepository.findById(id);
        if (existingCreator.isEmpty()) {
            return false;
        }

        clientRepository.delete(existingCreator.get());

        return !clientRepository.existsById(id);
    }





    @Override
    public boolean checkLogin(String username, String password) {

        Client existingCreator = clientRepository.findClientByUserName(username);
        if (existingCreator == null) {

            return false;}




        if (passwordEncoder.matches(password, existingCreator.getPassword())) {
            // Password matches, login successful
            return true;
        } else {

            return false;
        }
    }



    @Override
    public Client findByUsername(String username) {
        Client client = clientRepository.findClientByUserName(username);
        if(client != null){

            return client;
        }
        return null;
    }
}

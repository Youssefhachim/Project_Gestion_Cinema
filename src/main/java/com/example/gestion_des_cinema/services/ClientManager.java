package com.example.gestion_des_cinema.services;

import com.example.gestion_des_cinema.dao.entities.Client;

public interface ClientManager {

    public boolean checkLogin(String username, String password);
    public Client findByUsername(String username);
    public boolean deleteCeator(long id);
    public Client addClient(Client client);
}

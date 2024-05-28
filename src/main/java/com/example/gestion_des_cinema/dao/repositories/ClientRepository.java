package com.example.gestion_des_cinema.dao.repositories;

import com.example.gestion_des_cinema.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findClientByUserName(String Username);
}

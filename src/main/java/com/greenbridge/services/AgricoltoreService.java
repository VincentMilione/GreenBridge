package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import org.apache.catalina.users.AbstractGroup;

import java.util.List;

public interface AgricoltoreService {

    List<Agricoltore> getAgricoltori();
    Agricoltore saveAgricoltore(Agricoltore agricoltore);
    void modificaAgricoltore(Agricoltore agricoltore);
    Agricoltore getSingleAgricoltore(int id);


    void deleteAgricoltore(int id);

    public Agricoltore getAgricoltoreByEmail(String email);

    public Boolean AgricoltoreExistsByEmail(Agricoltore agricoltore);
}

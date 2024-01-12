package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import java.util.List;

public interface AgricoltoreService {
    List<Agricoltore> getAgricoltori();
    Agricoltore saveAgricoltore(Agricoltore agricoltore);
    void modificaAgricoltore(Agricoltore agricoltore);
    Agricoltore getSingleAgricoltore(int id);
    Agricoltore getAgricoltoreByEmail(String email);


}

package com.store.pharmacy.Client.service;

import com.store.pharmacy.Client.model.ClientInput;
import com.store.pharmacy.Client.model.ClientOutput;

public interface ClientService {
    ClientOutput create(ClientInput clientInput);
    ClientOutput update(String idClient, ClientInput clientInput);
}

package com.store.pharmacy.client.service;

import com.store.pharmacy.client.model.ClientInput;
import com.store.pharmacy.client.model.ClientOutput;

public interface ClientService {
    ClientOutput create(ClientInput clientInput);
    ClientOutput update(String idClient, ClientInput clientInput);
}

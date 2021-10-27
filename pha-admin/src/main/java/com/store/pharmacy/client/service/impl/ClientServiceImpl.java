package com.store.pharmacy.client.service.impl;

import com.store.pharmacy.client.model.Client;
import com.store.pharmacy.client.model.ClientInput;
import com.store.pharmacy.client.model.ClientOutput;
import com.store.pharmacy.client.repository.ClientRepository;
import com.store.pharmacy.client.service.ClientService;
import com.store.pharmacy.securities.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ClientOutput create(ClientInput clientInput) {
        Client client =  modelMapper.map(clientInput, Client.class);
        //TODO get user create by auth
        String[] firstName = clientInput.getFirstName().split(" ");
        String userName = userRepository.generateUserName(firstName[firstName.length - 1].charAt(0), clientInput.getLastName());
        client.setUserName(userName);
        String password = passwordEncoder.encode(clientInput.getPhoneNumber());
        client.setPassword(password);
        String userId = userRepository.generateUserId();
        client.setIdClient(userId);
        client.setCreatedAt(LocalDateTime.now());
        Client outParam = clientRepository.save(client);
        ClientOutput clientOutput =  modelMapper.map(outParam, ClientOutput.class);
        clientOutput.setUserId(outParam.getCreatedBy());
        clientOutput.setUpdatedAt(outParam.getCreatedAt());
        return clientOutput;
    }

    @Override
    public ClientOutput update(String idClient, ClientInput clientInput) {
        Client client = clientRepository.findById(idClient).orElse(null);
        if(client == null){
            throw new EntityExistsException();
        }
        Client clientMapper =  modelMapper.map(clientInput, Client.class);
        //TODO get user create by auth
        clientMapper.setIdClient(idClient);
        clientMapper.setUpdatedAt(LocalDateTime.now());
        Client outParam = clientRepository.save(clientMapper);
        ClientOutput clientOutput =  modelMapper.map(outParam, ClientOutput.class);
        clientOutput.setUserId(outParam.getUpdatedBy());
        return clientOutput;
    }
}
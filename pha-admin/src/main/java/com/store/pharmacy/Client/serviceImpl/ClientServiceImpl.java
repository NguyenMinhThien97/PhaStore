package com.store.pharmacy.Client.serviceImpl;

import com.store.pharmacy.Client.model.Client;
import com.store.pharmacy.Client.model.ClientInput;
import com.store.pharmacy.Client.model.ClientOutput;
import com.store.pharmacy.Client.repository.ClientRepository;
import com.store.pharmacy.Client.service.ClientService;
import com.store.pharmacy.securities.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

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
        client.setCreatedAt(LocalDate.now());
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
        clientMapper.setUpdatedAt(LocalDate.now());
        Client outParam = clientRepository.save(clientMapper);
        ClientOutput clientOutput =  modelMapper.map(outParam, ClientOutput.class);
        clientOutput.setUserId(outParam.getUpdatedBy());
        return clientOutput;
    }
}

package com.tcc.backend.service;

import com.tcc.backend.model.Address;
import com.tcc.backend.model.User;
import com.tcc.backend.repository.AddressRepository;
import com.tcc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    public AddressRepository addressRepository;
    @Autowired
    public UserRepository userRepository;


    public Address updateUserAddress(Address address, Long userId) {
        if(!userRepository.findById(userId).isPresent()){
            return null;
        }
        User user = userRepository.findById(userId).get();

        address.setUser(user);
        user.setAddress(address);
        userRepository.save(user);
        return addressRepository.save(address);
    }
}

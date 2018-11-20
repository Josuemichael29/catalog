package com.cdis.microservice.example.auth.service;

//import com.cdis.microservice.example.auth.event.EventDispatcher;
import com.cdis.microservice.example.auth.exception.ResourceNotFoundException;
import com.cdis.microservice.example.auth.model.User;
import com.cdis.microservice.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    //private EventDispatcher eventDispatcher;

    /*@Autowired
    public UserServiceImpl(UserRepository userRepository, EventDispatcher eventDispatcher) {
        this.userRepository = userRepository;
        this.eventDispatcher = eventDispatcher;
    }*/

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUsernameByUsername(String username) {
        User deletedUser = findUserByUsername(username);
        userRepository.delete(deletedUser);
    }

    @Override
    public void deleteUsernameById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String sendUserCredentials(String username) {
        /*User userCredentials = userRepository.findUserByUsername(username);

        if(userCredentials == null){
            throw new ResourceNotFoundException("User", "username", username);
        }

        eventDispatcher.send(userCredentials);
        return "USER SENT = [ " + userCredentials.toString() + "]";*/
        return null;
    }
}

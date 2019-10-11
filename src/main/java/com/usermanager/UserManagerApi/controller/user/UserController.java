package com.usermanager.UserManagerApi.controller.user;

import com.mongodb.MongoWriteException;
import com.usermanager.UserManagerApi.model.user.User;
import com.usermanager.UserManagerApi.repository.user.UserRepository;
import com.usermanager.UserManagerApi.service.user.UserService;
import com.usermanager.UserManagerApi.utils.OrderBy;
import com.usermanager.UserManagerApi.utils.SortedBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> result = userService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("sortUser/{sortedByParam}/{orderBy}")
    public ResponseEntity<?> sortBy(@PathVariable("sortedByParam") SortedBy sortedByParam, @PathVariable("orderBy")OrderBy orderBy) {
        List<User> result = userService.sort(sortedByParam,orderBy);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
        User result = userService.findOneByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable("userId") String userId) {
        User result = userService.findOneById(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userService.saveOrUpdateUser(user);
        }catch (MongoWriteException msg){
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User added succcessfully", HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        try {
            user.setId(userId);
            User userToBeUpdated = userService.findOneById(userId);
            if(user.getFirstName().isEmpty()){
                    user.setFirstName(userToBeUpdated.getFirstName());
            }
            if(user.getLastName().isEmpty()){
                user.setLastName(userToBeUpdated.getLastName());
            }
            if(user.getEmail().isEmpty()){
                user.setEmail(userToBeUpdated.getEmail());
            }
            if(user.getDateOfBirth() == null){
                user.setDateOfBirth(userToBeUpdated.getDateOfBirth());
            }
            userService.saveOrUpdateUser(user);
        }catch (MongoWriteException msg){
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User Updated succcessfully", HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("id") String id) {
        userService.deleteUser(id);
    }


}

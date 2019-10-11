package com.usermanager.UserManagerApi.service.user;

import com.usermanager.UserManagerApi.model.user.User;
import com.usermanager.UserManagerApi.repository.user.UserRepository;
import com.usermanager.UserManagerApi.utils.OrderBy;
import com.usermanager.UserManagerApi.utils.SortUser;
import com.usermanager.UserManagerApi.utils.SortedBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findOneById(String userId) {
        return userRepository.findOneById(userId);
    }

    @Override
    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String  id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> sort(SortedBy sortedByParam, OrderBy orderBy) {

            List<User> allUsers = userRepository.findAll();
            List<User> sortedUsers;

            switch (sortedByParam){
                case ID:
                    sortedUsers= SortUser.sortUser(allUsers,User::getId, orderBy);
                    break;
                case FIRSTNAME:
                    sortedUsers= SortUser.sortUser(allUsers,User::getFirstName, orderBy);
                    break;
                case LASTNAME:
                    sortedUsers= SortUser.sortUser(allUsers,User::getLastName, orderBy);
                    break;
                case EMAIL:
                    sortedUsers= SortUser.sortUser(allUsers,User::getEmail, orderBy);
                    break;
                case DATEOFBIRHT:
                    sortedUsers= SortUser.sortUser(allUsers,User::getDateOfBirth, orderBy);
                    break;
                default:
                    sortedUsers= SortUser.sortUser(allUsers,User::getId, orderBy);
            }
            return sortedUsers;
    }
}

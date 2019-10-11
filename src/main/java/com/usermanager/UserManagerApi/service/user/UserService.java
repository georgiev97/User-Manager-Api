package com.usermanager.UserManagerApi.service.user;

import com.usermanager.UserManagerApi.model.user.User;
import com.usermanager.UserManagerApi.utils.OrderBy;
import com.usermanager.UserManagerApi.utils.SortedBy;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findOneByEmail(String email);

    User findOneById (String userId);

    void saveOrUpdateUser(User user);

    void deleteUser(String id);

    List<User> sort(SortedBy sortedByParam, OrderBy order);
}

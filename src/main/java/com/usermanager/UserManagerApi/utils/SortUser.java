package com.usermanager.UserManagerApi.utils;

import com.usermanager.UserManagerApi.model.user.User;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortUser {

    public static List<User> sortUser(List<User> users, Function<User, Comparable> sortKey, OrderBy orderBy) {
        List<User> sortedUsers;
        if(orderBy.equals(OrderBy.ASC)) {
            sortedUsers  = (List<User>) users.stream().sorted(Comparator.comparing(sortKey)).collect(Collectors.toList());
        } else if (orderBy.equals(OrderBy.DESC)){
            sortedUsers = (List<User>) users.stream().sorted(Comparator.comparing(sortKey).reversed()).collect(Collectors.toList());
        }else {
            sortedUsers = users.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        }
        return sortedUsers;
    }

}

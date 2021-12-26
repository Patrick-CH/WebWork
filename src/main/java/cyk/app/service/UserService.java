package cyk.app.service;

import cyk.app.DAO.UserDAO;
import cyk.app.bean.User;

import java.io.IOException;
import java.util.List;

public class UserService extends BaseService{
    UserDAO userDAO;

    public UserService() throws IOException {
        userDAO = new UserDAO();
    }

    public int addUser(User new_user){
        if (null == userDAO.getUser(new_user.getUsername())){
            userDAO.newUser(new_user);
            return 0;
        } else {
            return -1;
        }
    }

    public int editUserInfo(User user_info){
        User original = userDAO.getUser(user_info.getUsername());
        if (null != original){
            user_info.setPassword(original.getPassword());
            userDAO.updateUser(user_info);
            return 0;
        } else {
            return -1;
        }
    }

    public int editUserPass(User user_info) {
        User original = userDAO.getUser(user_info.getUsername());
        if (null != original) {
            original.setPassword(user_info.getPassword());
            userDAO.updateUser(original);
            return 0;
        } else {
            return -1;
        }
    }

    public int removeUser(String username){
        if (null != userDAO.getUser(username)){
            userDAO.deleteUser(username);
            return 0;
        } else {
            return -1;
        }
    }

    public User findUser(String username){
        return userDAO.getUser(username);
    }

    public List<User> findAllUser(int numInPage, int page){
        List<User> r = userDAO.getAllUsers();
        return r.subList(page * numInPage, (page + 1) * numInPage);
    }

    public List<User> findAllUser(){
        return userDAO.getAllUsers();
    }

    public List<User> findUsersLike(String s){
        return userDAO.getAllUsersLike(s);
    }

    public static void main(String[] args) {
        UserService us = null;
        try {
            us = new UserService();
            if(0 == us.removeUser("Tom")){
                System.out.println("success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

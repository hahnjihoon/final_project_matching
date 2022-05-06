package com.test.matching.user.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.matching.common.SearchDate;
import com.test.matching.user.model.dao.UserDao;
import com.test.matching.user.model.vo.User;

@Service("UserService")
public class UserServiceImpl implements UserService {
   
      @Autowired
      private UserDao userDao;
      
      @Override
      public User selectLogin(User user) {
         return userDao.selectLogin(user);
      }

      @Override
      public int insertUser(User user) {
         return userDao.insertUser(user);
      }

      @Override
      public int selectDupCheckId(String userid) {
         return userDao.selectDupCheckId(userid);
      }
      
      @Override
      public int selectDupCheckNick(String nick) {
         return userDao.selectDupCheckNick(nick);
      }

      @Override
      public int updateUser(User user) {
         return userDao.updateUser(user);
      }

      @Override
      public int deleteUser(String userid) {
         return userDao.deleteUser(userid);
      }

      @Override
      public ArrayList<User> selectList() {
         return userDao.selectList();
      }

      @Override
      public User selectUser(String userid) {
         return userDao.selectUser(userid);
      }

      @Override
      public int updateLoginOk(User user) {
         return userDao.updateLoginOk(user);
      }

      @Override
      public ArrayList<User> selectSearchUserid(String keyword) {
         return userDao.selectSearchUserid(keyword);
      }

      @Override
      public ArrayList<User> selectSearchGender(String keyword) {
         return userDao.selectSearchGender(keyword);
      }

      @Override
      public ArrayList<User> selectSearchAge(int age) {
         return userDao.selectSearchAge(age);
      }

      @Override
      public ArrayList<User> selectSearchEnrollDate(SearchDate searchDate) {
         return userDao.selectSearchEnrollDate(searchDate);
      }

      @Override
      public ArrayList<User> selectSearchLoginOk(String keyword) {
         return userDao.selectSearchLoginOk(keyword);

      }
      
      @Override
      public ArrayList<User> selectList2() {
         return userDao.selectList2();
      }

}
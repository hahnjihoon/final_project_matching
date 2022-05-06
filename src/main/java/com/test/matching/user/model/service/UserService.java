package com.test.matching.user.model.service;

import java.util.ArrayList;

import com.test.matching.common.SearchDate;
import com.test.matching.user.model.vo.User;

public interface UserService {
      User selectLogin(User user);
      int insertUser(User user);
      int selectDupCheckId(String userid); //ȸ�����Խ� ���̵��ߺ�üũ�޼ҵ�
      int selectDupCheckNick(String nick); //ȸ�����Խ� �г����ߺ�üũ�޼ҵ�
      int updateUser(User user);
      int deleteUser(String userid);
      ArrayList<User> selectList();
      User selectUser(String userid);
      int updateLoginOk(User user);
      ArrayList<User> selectSearchUserid(String keyword);
      ArrayList<User> selectSearchGender(String keyword);
      ArrayList<User> selectSearchAge(int age);
      ArrayList<User> selectSearchEnrollDate(SearchDate searchDate);
      ArrayList<User> selectSearchLoginOk(String keyword);
      ArrayList<User> selectList2();
}
package com.test.matching.user.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.matching.common.SearchDate;
import com.test.matching.user.model.vo.User;

@Repository("userDao")
public class UserDao {
      @Autowired
      private SqlSessionTemplate session;
      

      public UserDao() {}
      
      //占쌨소듸옙占� 占쏙옙占쏙옙占십곤옙 占싫곤옙占쏙옙   
      public User selectLogin(User user) {
         return session.selectOne("userMapper.selectLogin", user);
      }
      
      public int count() {
	     return session.selectOne("userMapper.count");
	  }
	  
	  public int count1() {
	     return session.selectOne("userMapper.count1");
	  }
    	  
      public int insertUser(User user) {
         return session.insert("userMapper.insertUser", user);
      }

      public int selectDupCheckId(String userid) {
         return session.selectOne("userMapper.selectCheckId", userid);
      }
      
      public int selectDupCheckNick(String nick) {
            return session.selectOne("userMapper.selectCheckNick", nick);
      }

      public int updateUser(User user) {
         return session.update("userMapper.updateUser", user);
      }

      public int deleteUser(String userid) {
         return session.delete("userMapper.deleteUser", userid);
      }

      public ArrayList<User> selectList() {
         List<User> list = session.selectList("userMapper.selectList");
         return (ArrayList<User>)list;
      }

      public User selectUser(String userid) {
         return session.selectOne("userMapper.selectUser", userid);
      }

      public int updateLoginOk(User user) {
         return session.update("userMapper.updateLoginOk", user);
      }

      public ArrayList<User> selectSearchUserid(String keyword) {
         List<User> list = session.selectList("userMapper.selectSearchUserid", keyword);
         return (ArrayList<User>)list;
      }

      public ArrayList<User> selectSearchGender(String keyword) {
         List<User> list = session.selectList("userMapper.selectSearchGender", keyword);
         return (ArrayList<User>)list;
      }

      public ArrayList<User> selectSearchAge(int age) {
         List<User> list = session.selectList("userMapper.selectSearchAge", age);
         return (ArrayList<User>)list;
      }

      public ArrayList<User> selectSearchEnrollDate(SearchDate searchDate) {
         List<User> list = session.selectList("userMapper.selectSearchEnrollDate", searchDate);
         return (ArrayList<User>)list;
      }

      public ArrayList<User> selectSearchLoginOk(String keyword) {
         List<User> list = session.selectList("userMapper.selectSearchLoginOk", keyword);
         return (ArrayList<User>)list;
      }
      
      public ArrayList<User> selectList2() {
          List<User> list = session.selectList("userMapper.selectList2");
          return (ArrayList<User>)list;
       }

}
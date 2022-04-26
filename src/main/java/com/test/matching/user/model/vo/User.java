package com.test.matching.user.model.vo;

import java.sql.Date;

public class User implements java.io.Serializable{

	/**
	 *  User Á¤º¸
	 */
	private static final long serialVersionUID = -525518509780151973L;
	
	private String userid;
	private String userpwd;
	private String username;
	private String email;
	private int phone;
	private String img_name;
	private String myeyes;
	private String mynose;
	private String mymouth;
	private String nick;
	private int height;
	private String gender;
	private String address;
	private int age;
	private String hobby;
	private String figure;
	private String userjob;
	private String img_name2;
	private String eyes;
	private String nose;
	private String mouth;
	private java.sql.Date enroll_date;
	private String admin;
	private String block;
	private String userlike;
	private java.sql.Date lastmodified;
	private String login_ok;
	
	public User() {}

	public User(String userid, String userpwd, String username, String email, int phone, String img_name, String myeyes,
			String mynose, String mymouth, String nick, int height, String gender, String address, int age,
			String hobby, String figure, String userjob, String img_name2, String eyes, String nose, String mouth,
			Date enroll_date, String admin, String block, String userlike, Date lastmodified, String login_ok) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.img_name = img_name;
		this.myeyes = myeyes;
		this.mynose = mynose;
		this.mymouth = mymouth;
		this.nick = nick;
		this.height = height;
		this.gender = gender;
		this.address = address;
		this.age = age;
		this.hobby = hobby;
		this.figure = figure;
		this.userjob = userjob;
		this.img_name2 = img_name2;
		this.eyes = eyes;
		this.nose = nose;
		this.mouth = mouth;
		this.enroll_date = enroll_date;
		this.admin = admin;
		this.block = block;
		this.userlike = userlike;
		this.lastmodified = lastmodified;
		this.login_ok = login_ok;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getMyeyes() {
		return myeyes;
	}

	public void setMyeyes(String myeyes) {
		this.myeyes = myeyes;
	}

	public String getMynose() {
		return mynose;
	}

	public void setMynose(String mynose) {
		this.mynose = mynose;
	}

	public String getMymouth() {
		return mymouth;
	}

	public void setMymouth(String mymouth) {
		this.mymouth = mymouth;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public String getUserjob() {
		return userjob;
	}

	public void setUserjob(String userjob) {
		this.userjob = userjob;
	}

	public String getImg_name2() {
		return img_name2;
	}

	public void setImg_name2(String img_name2) {
		this.img_name2 = img_name2;
	}

	public String getEyes() {
		return eyes;
	}

	public void setEyes(String eyes) {
		this.eyes = eyes;
	}

	public String getNose() {
		return nose;
	}

	public void setNose(String nose) {
		this.nose = nose;
	}

	public String getMouth() {
		return mouth;
	}

	public void setMouth(String mouth) {
		this.mouth = mouth;
	}

	public java.sql.Date getEnroll_date() {
		return enroll_date;
	}

	public void setEnroll_date(java.sql.Date enroll_date) {
		this.enroll_date = enroll_date;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getUserlike() {
		return userlike;
	}

	public void setUserlike(String userlike) {
		this.userlike = userlike;
	}

	public java.sql.Date getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(java.sql.Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getLogin_ok() {
		return login_ok;
	}

	public void setLogin_ok(String login_ok) {
		this.login_ok = login_ok;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", email=" + email
				+ ", phone=" + phone + ", img_name=" + img_name + ", myeyes=" + myeyes + ", mynose=" + mynose
				+ ", mymouth=" + mymouth + ", nick=" + nick + ", height=" + height + ", gender=" + gender + ", address="
				+ address + ", age=" + age + ", hobby=" + hobby + ", figure=" + figure + ", userjob=" + userjob
				+ ", img_name2=" + img_name2 + ", eyes=" + eyes + ", nose=" + nose + ", mouth=" + mouth
				+ ", enroll_date=" + enroll_date + ", admin=" + admin + ", block=" + block + ", userlike=" + userlike
				+ ", lastmodified=" + lastmodified + ", login_ok=" + login_ok + "]";
	}
	
	

	
	
	

	
	

}
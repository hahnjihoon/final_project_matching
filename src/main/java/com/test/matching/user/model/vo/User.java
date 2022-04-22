package com.test.matching.user.model.vo;

import java.sql.Date;

public class User implements java.io.Serializable{

	/**
	 *  User Á¤º¸
	 */
	private static final long serialVersionUID = -525518509780151973L;
	
	private String user_id;
	private String pwd;
	private String name;
	private String email;
	private int phone;
	private String img_name;
	private String nick;
	private int height;
	private String gender;
	private String address;
	private int age;
	private String hobby;
	private String figure;
	private String job;
	private String img_name2;
	private String eyes;
	private String nose;
	private String mouth;
	private java.sql.Date date;
	private String admin;
	private String block;
	private String like;
	
	public User() {}

	public User(String user_id, String pwd, String name, String email, int phone, String img_name, String nick,
			int height, String gender, String address, int age, String hobby, String figure, String job,
			String img_name2, String eyes, String nose, String mouth, Date date, String admin, String block,
			String like) {
		super();
		this.user_id = user_id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.img_name = img_name;
		this.nick = nick;
		this.height = height;
		this.gender = gender;
		this.address = address;
		this.age = age;
		this.hobby = hobby;
		this.figure = figure;
		this.job = job;
		this.img_name2 = img_name2;
		this.eyes = eyes;
		this.nose = nose;
		this.mouth = mouth;
		this.date = date;
		this.admin = admin;
		this.block = block;
		this.like = like;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
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

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", img_name=" + img_name + ", nick=" + nick + ", height=" + height + ", gender=" + gender
				+ ", address=" + address + ", age=" + age + ", hobby=" + hobby + ", figure=" + figure + ", job=" + job
				+ ", img_name2=" + img_name2 + ", eyes=" + eyes + ", nose=" + nose + ", mouth=" + mouth + ", date="
				+ date + ", admin=" + admin + ", block=" + block + ", like=" + like + "]";
	}

	
	

	
	

}
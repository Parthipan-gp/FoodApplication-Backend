package com.niit.FavoriteService.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String  userEmail;
    private String userPassword;
    private String  userName;
    private long  userPhoneNo;
    private byte[] userImage;
    private List<Restaurant> restaurantList;

    public User(){

    }

    public User(String userEmail, String userPassword, String userName, long userPhoneNo, byte[] userImage, List<Restaurant> restaurantList) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.userImage = userImage;
        this.restaurantList = restaurantList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(long userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhoneNo=" + userPhoneNo +
                ", userImage='" + userImage + '\'' +
                ", restaurantList=" + restaurantList +
                '}';
    }
}

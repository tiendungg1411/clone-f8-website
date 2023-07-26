/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Account {
    private String id;
    private String userName;
    private String password;
    private String mail;
    private String bio;
    private String avatar;
    private String url;
    private String verificationCode;
    private String registrationDate;
    private String firstAndLastName;
    private String coverImage;
    private String roleID;
    private String phoneNumber;

    public Account() {
    }

    public Account(String id, String mail, String avatar) {
        this.id = id;
        this.mail = mail;
        this.avatar = avatar;
    }

    
    
    public Account(String id, String userName, String password, String mail, String bio, String avatar, String url, String verificationCode, String registrationDate, String firstAndLastName, String coverImage, String roleID, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.bio = bio;
        this.avatar = avatar;
        this.url = url;
        this.verificationCode = verificationCode;
        this.registrationDate = registrationDate;
        this.firstAndLastName = firstAndLastName;
        this.coverImage = coverImage;
        this.roleID = roleID;
        this.phoneNumber = phoneNumber;
    }
    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", mail=" + mail + ", bio=" + bio + ", avatar=" + avatar + ", url=" + url + ", verificationCode=" + verificationCode + ", registrationDate=" + registrationDate + ", firstAndLastName=" + firstAndLastName + ", coverImage=" + coverImage + ", roleID=" + roleID + ", phoneNumber=" + phoneNumber + '}';
    }
    
    

    


    
}

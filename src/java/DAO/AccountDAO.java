/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.Account;
import Model.CourseAccount;
import Model.Setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN DAT
 */
public class AccountDAO {
    Connection connect = null;

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> listA = new ArrayList<>();
        String sql = "select * from account";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            System.out.println("getAllCourse: " + e.getMessage());
        }
        return listA;
    }

    //Khai bao cac thanh phan xu ly DB
    Connection cnn;//Ket noi DB
    Statement stm;//Thuc thi cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//Luu tru va xu ly du lieu

    public String getPassWordByAccount(String username) {
        try {
            String strSelect = "select * from account where username = ?";
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(strSelect);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String password = rs.getString(3);
                return password;
            }
        } catch (Exception e) {
            System.out.println("getPassWordByAccount" + e.getMessage());
        }
        return "";
    }

    public void update(Account account) {
        String sql = "UPDATE Account SET password = ? WHERE userName = ?";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, account.getPassword());
            pstm.setString(2, account.getUserName());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("update Account: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
        //  ArrayList<Account> al = a.getListAccount();
//        a.createPhone("haha", "0567", "Lê Manh Tri","image/default_cover.png","2");
//        a.createFacebook("kaka", "7162", "hshsas", "image/default_cover.png", "2");
//        ArrayList<Account> l = a.getAccout();
//            for (Account account : l) {
//                System.out.println(account);
//            }        
//        System.out.println(a.checkUserPhone("undefined"));;
        // a.update(new Account("dat310", "1234"));
    }

    /* Dung */
 /* Get list account*/
    public ArrayList<Account> getListAccount() {
        ArrayList<Account> listAccount = new ArrayList<Account>();
        try {
            /* Select list account from Account table */
            String strSelect = "select * from Account";
            connect = DBContext.getConnection();
            pstm = connect.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            /* Loop to get data */
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("mail");
                String bio = rs.getString("bio");
                String avatar = rs.getString("avatar");
                String url = rs.getString("url");
                String verificationCode = "";
                /* Check verfication code is null */
                if (rs.getString("verificationCode") != null) {
                    verificationCode = rs.getString("verificationCode");
                }
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String joinDate = date.format(rs.getDate("registrationDate"));
                String firstAndLastName = rs.getString("firstAndLastName");
                String coverImage = rs.getString("coverImage");
                String roleId = String.valueOf(rs.getInt("roleId"));

                /* Add data to list account */
                listAccount.add(new Account(id, userName, password, email, bio,
                        avatar, url, verificationCode, joinDate,
                        firstAndLastName, coverImage, roleId, phoneNumber));
            }
        } catch (Exception e) {
            System.out.println("getListAccount: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listAccount;
    }

    /*Get list actor */
    public ArrayList<Setting> getListActor() {
        ArrayList<Setting> listActor = new ArrayList<Setting>();
        try {
            /* Select list actor from Setting table */
            String strSelect = "select * from Setting";
            connect = DBContext.getConnection();
            pstm = connect.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            /* Loop to get data */
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String actor = rs.getString(2);

                /* Add data to list actor */
                listActor.add(new Setting(id, actor));
            }
        } catch (Exception e) {
            System.out.println("getListActor: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listActor;
    }

    /* Update actor by user id */
    public void updateActor(String userId, String roleId) {
        try {
            /* Update role id by user id */
            String strUpdate = "update Account\n"
                    + "set roleID = ?\n"
                    + "where id = ?";
            connect = DBContext.getConnection();
            pstm = connect.prepareStatement(strUpdate);
            pstm.setString(1, roleId);
            pstm.setString(2, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateActor: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /* Get information of user by user id */
    public Account getInforUser(String userId) {
        Account inforUser = new Account();
        try {
            String strSelect = "select * from Account where id = ?";
            connect = DBContext.getConnection();
            pstm = connect.prepareStatement(strSelect);
            pstm.setString(1, userId);
            rs = pstm.executeQuery();
            /* Loop to get data */
            while (rs.next()) {
                inforUser.setId(String.valueOf(rs.getInt("id")));
                inforUser.setUserName(rs.getString("username"));
                inforUser.setPassword(rs.getString("password"));
                inforUser.setPhoneNumber(rs.getString("phone"));
                inforUser.setMail(rs.getString("mail"));
                inforUser.setBio(rs.getString("bio"));
                inforUser.setAvatar(rs.getString("avatar"));
                inforUser.setUrl(rs.getString("url"));
                inforUser.setVerificationCode("");
                /* Check verfication code is null */
                if (rs.getString("verificationCode") != null) {
                    inforUser.setVerificationCode(rs.getString("verificationCode"));
                }
                /* Format date to dd-MM-yyyy */
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                inforUser.setRegistrationDate(date.format(rs.getDate("registrationDate")));
                inforUser.setFirstAndLastName(rs.getString("firstAndLastName"));
                inforUser.setCoverImage(rs.getString("coverImage"));
                inforUser.setRoleID(rs.getString("roleID"));
            }
        } catch (Exception e) {
            System.out.println("getInforUser: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return inforUser;
    }

    /* Update cover image by usere id */
    public void updateCoverImg(String userId, String urlCoverImg) {
        try {
            String strUpdate = "update Account\n"
                    + " set coverImage = ?\n"
                    + " where id = ?";
            connect = DBContext.getConnection();
            pstm = connect.prepareStatement(strUpdate);
            pstm.setString(1, urlCoverImg);
            pstm.setString(2, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateCoverImg: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /* Get course of account */
    public ArrayList<CourseAccount> getCourseOfAccount(String userId) {
        ArrayList<CourseAccount> listCourse = new ArrayList<CourseAccount>();
        try {
            String strSelect = "select * from CourseAccount where accountID = ?";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, userId);
            rs = pstm.executeQuery();
            /*Loop to get Course */
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String accountId = String.valueOf(rs.getInt(2));
                String courseId = String.valueOf(rs.getInt(3));
                /* Add data to list course of account */
                listCourse.add(new CourseAccount(id, accountId, courseId));
            }
        } catch (Exception e) {
            System.out.println("getCourseOfAccount: " + e.getMessage());
        }
        return listCourse;
    }

    /* Get list account by actor id */
    public ArrayList<Account> getListAccountByActorId(String actorId) {
        ArrayList<Account> listAccount = new ArrayList<Account>();
        try {
            String strSelect = "select * from Account where roleID = ? ";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, actorId);
            rs = pstm.executeQuery();
            /* Loop to get data */
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("mail");
                String bio = rs.getString("bio");
                String avatar = rs.getString("avatar");
                String url = rs.getString("url");
                String verificationCode = "";
                /* Check verfication code is null */
                if (rs.getString("verificationCode") != null) {
                    verificationCode = rs.getString("verificationCode");
                }
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String joinDate = date.format(rs.getDate("registrationDate"));
                String firstAndLastName = rs.getString("firstAndLastName");
                String coverImage = rs.getString("coverImage");
                String roleId = String.valueOf(rs.getInt("roleID"));

                /* Add data to list account */
                listAccount.add(new Account(id, userName, password, email, bio,
                        avatar, url, verificationCode, joinDate,
                        firstAndLastName, coverImage, roleId, phoneNumber));

            }
        } catch (Exception e) {
            System.out.println("getListAccountByActorId: " + e.getMessage());
        }
        return listAccount;
    }

    /* Update data by user id */
    public void updateInforUser(String userId, String name, String bio, String avatar, String username, String email, String phoneNumber) {
        try {
            String strUpdate = "update Account\n"
                    + " set firstAndLastName = ?,\n"
                    + " bio = ?,\n"
                    + " avatar = ?,\n"
                    + " userName = ?,\n"
                    + " mail = ?,\n"
                    + " phone = ?\n"
                    + " where id = ?";
            connect = DBContext.getConnection();
            pstm = connect.prepareStatement(strUpdate);
            pstm.setString(1, name);
            pstm.setString(2, bio);
            pstm.setString(3, avatar);
            pstm.setString(4, username);
            pstm.setString(5, email);
            pstm.setString(6, phoneNumber);
            pstm.setString(7, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateInforUser: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /* Update name and bio by user id */
    public void updateNameAndBio(String userId, String name, String bio, String username, String email, String phoneNumber) {
        try {
            String strUpdate = "update Account \n"
                    + "set firstAndLastName = ?,\n"
                    + "bio = ?,\n"
                    + "username = ?,\n"
                    + "mail = ?,\n"
                    + "phone = ?\n"
                    + "where id =?";
            connect = DBContext.getConnection();
            pstm = connect.prepareStatement(strUpdate);
            pstm.setString(1, name);
            pstm.setString(2, bio);
            pstm.setString(3, username);
            pstm.setString(4, email);
            pstm.setString(5, phoneNumber);
            pstm.setString(6, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateNameAndBio: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /* Delete user by user id */
    public void deleteUser(String userId) {
        try {
            String strDelete = "delete from Account where id = ? ";
            pstm = getConnection().prepareStatement(strDelete);
            pstm.setString(1, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteUser: " + e.getMessage());
        }
    }

    /* Delete user of course account table by user id */
    public void deleteUserOfCourseAccount(String userId) {
        try {
            String strDelete = "delete from courseaccount where accountID = ?";
            pstm = getConnection().prepareStatement(strDelete);
            pstm.setString(1, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteUserOfCourseAccount: " + e.getMessage());
        }
    }

    /* Delete user of notification table by user id */
    public void deleteUserOfNotification1(String userId) {
        try {
            String strDelete = "delete from notification where fromAccount = ?";
            pstm = getConnection().prepareStatement(strDelete);
            pstm.setString(1, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteUserOfNotification: " + e.getMessage());
        }
    }

    public void deleteUserOfNotification2(String userId) {
        try {
            String strDelete = "delete from notification where recieveAccount = ?";
            pstm = getConnection().prepareStatement(strDelete);
            pstm.setString(1, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteUserOfNotification: " + e.getMessage());
        }
    }

    //Tri code
    //check mail exist
    public boolean checkUserEmail(String email) {
        try {
            String strSelect = "select * from account "
                    + "where mail=? ";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, email);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserEmail:" + e.getMessage());
        }
        return false;
    }

    public void createEmail(String username, String pass, String email, String firstAndLastName, String coverImage, String roleID) {
        try {
            String strSelect = "insert into account(userName, password, mail,firstAndLastName, coverImage, roleID,registrationDate)\n"
                    + "Values(?,?,?,?,?,?,NOW())";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, pass);
            pstm.setString(3, email);
            pstm.setString(4, firstAndLastName);
            pstm.setString(5, coverImage);
            pstm.setString(6, roleID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("createEmail:" + e.getMessage());
        }
    }

    public void createPhone(String username, String phone, String firstAndLastName, String coverImage, String roleID) {
        try {
            String strSelect = "insert into account(userName, phone,firstAndLastName, coverImage, roleID,registrationDate)\n"
                    + "Values(?,?,?,?,?,NOW())";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, phone);
            pstm.setString(3, firstAndLastName);
            pstm.setString(4, coverImage);
            pstm.setString(5, roleID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("createPhone:" + e.getMessage());
        }
    }

    public boolean checkUserFacebook(String facebookId) {
        try {
            String strSelect = "select * from account "
                    + "where facebookId=? ";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, facebookId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserFacebook:" + e.getMessage());
        }
        return false;
    }

    public void createFacebook(String username, String facebookId, String name, String avatar, String coverImage, String roleID) {
        try {
            String strSelect = "insert into account(userName, facebookId,firstAndLastName,avatar, coverImage, roleID, registrationDate)\n"
                    + "Values(?,?,?,?,?,?,NOW())";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, facebookId);
            pstm.setString(3, name);
            pstm.setString(4, avatar);
            pstm.setString(5, coverImage);
            pstm.setString(6, roleID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("createFacebook:" + e.getMessage());
        }
    }

    public boolean checkUserGoogle(String googleId) {
        try {
            String strSelect = "select * from account "
                    + "where googleId=? ";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, googleId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserGoogle:" + e.getMessage());
        }
        return false;
    }

    public void createGoogle(String username, String googleId, String name, String avatar, String coverImage, String roleID) {
        try {
            String strSelect = "insert into account(userName, googleId,firstAndLastName,avatar, coverImage, roleID, registrationDate)\n"
                    + "Values(?,?,?,?,?,?,NOW())";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, googleId);
            pstm.setString(3, name);
            pstm.setString(4, avatar);
            pstm.setString(5, coverImage);
            pstm.setString(6, roleID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("createGoogle:" + e.getMessage());
        }
    }

    public boolean checkUserGithub(String githubId) {
        try {
            String strSelect = "select * from account "
                    + "where githubId=? ";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, githubId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserGithub:" + e.getMessage());
        }
        return false;
    }

    public void createGithub(String username, String githubId, String name, String avatar, String coverImage, String roleID) {
        try {
            String strSelect = "insert into account(userName, githubId,firstAndLastName,avatar, coverImage, roleID, registrationDate)\n"
                    + "Values(?,?,?,?,?,?,NOW())";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, githubId);
            pstm.setString(3, name);
            pstm.setString(4, avatar);
            pstm.setString(5, coverImage);
            pstm.setString(6, roleID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("createGithub:" + e.getMessage());
        }
    }

    //Tri code
    //check mail exist
    public boolean checkUserPhone(String phone) {
        try {
            String strSelect = "select * from account "
                    + "where phone=? ";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, phone);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserPhone:" + e.getMessage());
        }
        return false;
    }

    public Account getAccountByEmailAndPwd(Account account) {
        try {
            String sql = "SELECT * FROM account where mail = ? and password = ?";
            pstm = getConnection().prepareStatement(sql);
            pstm.setString(1, account.getMail());
            pstm.setString(2, account.getPassword());
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("id"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("mail"),
                        rs.getString("bio"),
                        rs.getString("avatar"),
                        rs.getString("url"),
                        rs.getString("verificationCode"),
                        rs.getString("registrationDate"),
                        rs.getString("firstAndLastName"),
                        rs.getString("coverImage"),
                        rs.getString("roleID"),
                        rs.getString("phone"));
            }
        } catch (Exception e) {
            System.out.println("getAccountByEmailAndPwd:" + e.getMessage());
        }
        return null;
    }

    public Account getAccountByGitId(String githubId) {
        try {
            String sql = "SELECT * FROM db_f8.account where githubId = ?";
            pstm = getConnection().prepareStatement(sql);
            pstm.setString(1, githubId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("id"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("mail"),
                        rs.getString("bio"),
                        rs.getString("avatar"),
                        rs.getString("url"),
                        rs.getString("verificationCode"),
                        rs.getString("registrationDate"),
                        rs.getString("firstAndLastName"),
                        rs.getString("coverImage"),
                        rs.getString("roleID"),
                        rs.getString("phone"));
            }
        } catch (Exception e) {
            System.out.println("getAccountByEmailAndPwd:" + e.getMessage());
        }
        return null;
    }

    public Account getAccountByFbId(String facebookId) {
        try {
            String sql = "SELECT * FROM db_f8.account where facebookId = ?";
            pstm = getConnection().prepareStatement(sql);
            pstm.setString(1, facebookId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("id"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("mail"),
                        rs.getString("bio"),
                        rs.getString("avatar"),
                        rs.getString("url"),
                        rs.getString("verificationCode"),
                        rs.getString("registrationDate"),
                        rs.getString("firstAndLastName"),
                        rs.getString("coverImage"),
                        rs.getString("roleID"),
                        rs.getString("phone"));
            }
        } catch (Exception e) {
            System.out.println("getAccountByEmailAndPwd:" + e.getMessage());
        }
        return null;
    }

    public Account getAccountByGgId(String googleId) {
        try {
            String sql = "SELECT * FROM account where googleId = ?";
            pstm = getConnection().prepareStatement(sql);
            pstm.setString(1, googleId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("id"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("mail"),
                        rs.getString("bio"),
                        rs.getString("avatar"),
                        rs.getString("url"),
                        rs.getString("verificationCode"),
                        rs.getString("registrationDate"),
                        rs.getString("firstAndLastName"),
                        rs.getString("coverImage"),
                        rs.getString("roleID"),
                        rs.getString("phone"));
            }
        } catch (Exception e) {
            System.out.println("getAccountByEmailAndPwd:" + e.getMessage());
        }
        return null;
    }

    /* Search by username in "All" status */
    public ArrayList<Account> getAccountByUserName(String txtSearch) {
        ArrayList<Account> listAccount = new ArrayList<Account>();
        try {
            String strSelect = "SELECT * FROM account\n"
                    + "WHERE userName like ?";
            connect = DBContext.getConnection();
            PreparedStatement pstm = connect.prepareStatement(strSelect);
            pstm.setString(1, "%" + txtSearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("mail");
                String bio = rs.getString("bio");
                String avatar = rs.getString("avatar");
                String url = rs.getString("url");
                String verificationCode = "";
                /* Check verfication code is null */
                if (rs.getString("verificationCode") != null) {
                    verificationCode = rs.getString("verificationCode");
                }
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String joinDate = date.format(rs.getDate("registrationDate"));
                String firstAndLastName = rs.getString("firstAndLastName");
                String coverImage = rs.getString("coverImage");
                String roleId = String.valueOf(rs.getInt("roleID"));

                /* Add data to list account */
                listAccount.add(new Account(id, userName, password, email, bio,
                        avatar, url, verificationCode, joinDate,
                        firstAndLastName, coverImage, roleId, phoneNumber));

            }

        } catch (Exception e) {
            System.out.println("getAccountByUserName: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listAccount;
    }

    /* Search by username and actorID */
    public ArrayList<Account> getAccountByUserNameAndActorID(String actorID, String txtSearch) {
        ArrayList<Account> listAccount = new ArrayList<Account>();
        try {
            String strSelect = "SELECT * FROM account\n"
                    + "WHERE roleID = ? AND userName like ?";
            connect = DBContext.getConnection();
            PreparedStatement pstm = connect.prepareStatement(strSelect);
            pstm.setString(1, actorID);
            pstm.setString(2, "%" + txtSearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("mail");
                String bio = rs.getString("bio");
                String avatar = rs.getString("avatar");
                String url = rs.getString("url");
                String verificationCode = "";
                /* Check verfication code is null */
                if (rs.getString("verificationCode") != null) {
                    verificationCode = rs.getString("verificationCode");
                }
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String joinDate = date.format(rs.getDate("registrationDate"));
                String firstAndLastName = rs.getString("firstAndLastName");
                String coverImage = rs.getString("coverImage");
                String roleId = String.valueOf(rs.getInt("roleID"));

                /* Add data to list account */
                listAccount.add(new Account(id, userName, password, email, bio,
                        avatar, url, verificationCode, joinDate,
                        firstAndLastName, coverImage, roleId, phoneNumber));
            }
        } catch (Exception e) {
            System.out.println("getAccountByUserNameAndActorID: " + e.getMessage());
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listAccount;
    }

}

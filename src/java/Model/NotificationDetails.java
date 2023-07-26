/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class NotificationDetails {
    private int id;
    private String content;
    private String url;
    private Account fromAccount;
    private Account recieveAccount;
    private boolean isRead;

    public NotificationDetails() {
    }
    
    public NotificationDetails(int id, String content, String url, Account fromAccount, Account recieveAccount, boolean isRead) {
        this.id = id;
        this.content = content;
        this.url = url;
        this.fromAccount = fromAccount;
        this.recieveAccount = recieveAccount;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getRecieveAccount() {
        return recieveAccount;
    }

    public void setRecieveAccount(Account recieveAccount) {
        this.recieveAccount = recieveAccount;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    
    
}

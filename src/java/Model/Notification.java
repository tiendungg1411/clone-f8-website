/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Notification {
    private int id;
    private String content;
    private String url;
    private String fromAccount;
    private String receiveAccount;
    private boolean isRead;
    private String fromAccountName;
    private String fromAccountAvatar;

    public Notification() {
    }

    public Notification(int id, String content, String url, String fromAccount, String receiveAccount, boolean isRead, String fromAccountName, String fromAccountAvatar) {
        this.id = id;
        this.content = content;
        this.url = url;
        this.fromAccount = fromAccount;
        this.receiveAccount = receiveAccount;
        this.isRead = isRead;
        this.fromAccountName = fromAccountName;
        this.fromAccountAvatar = fromAccountAvatar;
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

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getFromAccountName() {
        return fromAccountName;
    }

    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName;
    }

    public String getFromAccountAvatar() {
        return fromAccountAvatar;
    }

    public void setFromAccountAvatar(String fromAccountAvatar) {
        this.fromAccountAvatar = fromAccountAvatar;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", content=" + content + ", url=" + url + ", fromAccount=" + fromAccount + ", receiveAccount=" + receiveAccount + ", isRead=" + isRead + ", fromAccountName=" + fromAccountName + ", fromAccountAvatar=" + fromAccountAvatar + '}';
    }
    
}

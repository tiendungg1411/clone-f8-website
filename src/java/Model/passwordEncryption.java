/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author DELL
 */
public class passwordEncryption {

    public static String encoder(String input) {
        String salt = "$2a$10$rBbHM187S9d2/pbh5zuu5e";
        String encoded = BCrypt.hashpw(input, salt);
        return encoded;
    }

    public static boolean isMatched(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

    public static void main(String[] args) {
        String password = "0377069365";
        String salt = BCrypt.gensalt();
        String encoded = BCrypt.hashpw(password, salt);
        boolean valuate = BCrypt.checkpw(password, encoded);
        System.out.println("salt: " + salt);
        System.out.println("encoded:" + encoded);
        System.out.println(valuate);
    }
}

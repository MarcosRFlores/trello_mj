package com.crud.trello_mj.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Encriptar una contraseña
    public static String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verificar una contraseña
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
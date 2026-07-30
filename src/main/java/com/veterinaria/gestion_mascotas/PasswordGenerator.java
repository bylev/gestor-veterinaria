package com.veterinaria.gestion_mascotas;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa la contraseña a hashear: ");
        String password = sc.nextLine();
        System.out.println("Esta es el hash de "+ password+ ": ");
        String hash = encoder.encode(password);
        System.out.println(hash);
    }
}
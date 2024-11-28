package com.backend.api_medic.infrastructure.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class GenerateCredentials {

    private String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String generateUsername(String fullName){
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        return getFirstNameWithInitials(fullName) + formattedDate;
    }

    public String generatePassword(String fullName){
        return getFirstNameWithInitials(fullName) + generateRandomString();
    }

    private String getFirstNameWithInitials(String fullName){
        String[] words = fullName.split("\\s+");
        if (words.length < 2) {
            throw new IllegalArgumentException("Full name must contain at least two words");
        }
        String firstName = words[0].toLowerCase();
        String lastInitials = words[words.length - 2].substring(0, 1).toLowerCase() +
                words[words.length - 1].substring(0, 1).toLowerCase();
        return firstName + "." + lastInitials;
    }

    private String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            builder.append(CHARACTERS.charAt(index));
        }
        return builder.toString();
    }

}
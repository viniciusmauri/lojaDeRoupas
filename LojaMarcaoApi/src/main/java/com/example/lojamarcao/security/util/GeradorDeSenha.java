package com.example.lojamarcao.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeSenha {
    
    public static void man(String[] args) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	System.out.println(encoder.encode("admin"));
    }

}

package com.example.lojamarcao.resource;

import com.example.lojamarcao.config.property.LojaMarcaoApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

    @Autowired
    private LojaMarcaoApiProperty lojaMarcaoProperty;

    @DeleteMapping("revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse resp) {
	Cookie cookie = new Cookie("refreshToken", null);
	cookie.setHttpOnly(true);
	cookie.setSecure(lojaMarcaoProperty.getSeguranca().isEnableHttps());
	cookie.setPath(req.getContextPath() + "/oauth/token");
	cookie.setMaxAge(0);

	resp.addCookie(cookie);
	resp.setStatus(HttpStatus.NO_CONTENT.value());
    }
}

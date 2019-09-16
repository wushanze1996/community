package com.wszstudy.community.controller;

import com.wszstudy.community.dto.AccessTokenDTO;
import com.wszstudy.community.dto.GithubUser;
import com.wszstudy.community.entity.User;
import com.wszstudy.community.mapper.UserMapper;
import com.wszstudy.community.provider.GithubProvider;
import com.wszstudy.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
   @Autowired
   private UserService userService;
   @Autowired
   private  UserMapper userMapper;
    @Value("${github.client_id}")
    String client_id;
    @Value("${github.client_secret}")
    String client_secret;
    @Value("${github.redirect_uri}")
    String redirect_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null)
        {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAvatar_url(githubUser.getAvatar_url());
            user.setAccount_id(String.valueOf(githubUser.getId()));
//            user.setGmt_create(System.currentTimeMillis());
//            user.setGmt_modified(user.getGmt_create());
            userService.createOrUpdate(user);
            //userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            return "redirect:/";
        }




    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie =new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";

    }


}

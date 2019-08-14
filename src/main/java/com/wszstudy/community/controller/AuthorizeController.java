package com.wszstudy.community.controller;

import com.wszstudy.community.dto.AccessTokenDTO;
import com.wszstudy.community.dto.GithubUser;
import com.wszstudy.community.entity.User;
import com.wszstudy.community.mapper.UserMapper;
import com.wszstudy.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    UserMapper userMapper;
    @Value("${github.client_id}")
    String client_id;
    @Value("${github.client_secret}")
    String client_secret;
    @Value("${github.redirect_uri}")
    String redirect_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,HttpServletRequest request){
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
//            User user = new User();
//            user.setToken(UUID.randomUUID().toString());
//            user.setName(githubUser.getName());
//            user.setAccount_id(String.valueOf(githubUser.getId()));
//            user.setGmt_create(System.currentTimeMillis());
//            user.setGmt_modified(user.getGmt_create());
//            userMapper.insert(user);
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);

            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            return "redirect:/";
        }




    }


}

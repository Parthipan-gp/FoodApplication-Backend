package com.niit.UserAuthenticationService.Security;

import com.niit.UserAuthenticationService.Domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    public Map<String,String> createToken(User user);
}

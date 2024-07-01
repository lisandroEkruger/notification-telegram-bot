package com.app.controllers;

import org.springframework.stereotype.Controller;

import com.app.model.AccessRequest;
import com.app.model.UserAccount;
import com.app.services.AccessRequestService;
import com.app.services.UserAccountService;

@Controller
public class UserController {

    private final UserAccountService userAccountService;
    private final AccessRequestService accessRequestService;

    public UserController(UserAccountService userAccountService, AccessRequestService accessRequestService) {
        this.userAccountService = userAccountService;
        this.accessRequestService = accessRequestService;
    }

    public UserAccount addUserAccount(AccessRequest request) {
        UserAccount user = new UserAccount();
        user.setActive(false);
        user.setChatId(request.getChatId());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastname(request.getLastname());

        UserAccount result = this.userAccountService.updateUserAccount(user);
        
        this.accessRequestService.deleteByChatId(request.getChatId());

        return result;
    }

    public UserAccount getUserAccountByChatId(Long chatid) {

        UserAccount result = this.userAccountService.getByChatId(chatid);

        return result;
    }
}

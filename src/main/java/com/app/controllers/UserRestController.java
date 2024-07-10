package com.app.controllers;

import com.app.bot.NotificatorBot;
import com.app.model.UserAccount;
import com.app.services.UserAccountService;
import com.app.utils.BotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserAccountService userAccountService;
    private final NotificatorBot notificatorBot;

    @Autowired
    public UserRestController(UserAccountService userAccountService, NotificatorBot notificatorBot) {
        this.userAccountService = userAccountService;
        this.notificatorBot = notificatorBot;
    }

    @GetMapping
    public List<UserAccount> getAllUserAccounts() {
        return userAccountService.getAllUserAccounts();
    }

    @PostMapping("/{chatId}/message")
    public ResponseEntity<Void> sendMessageToUser(@PathVariable Long chatId, @RequestBody String message) {

        UserAccount userAccount = userAccountService.getByChatId(chatId);

        if (userAccount == null) {
            return ResponseEntity.notFound().build();
        }

        if (!userAccount.getActive()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        BotUtils.sendMessage(notificatorBot, message, chatId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{chatId}/activate")
    public ResponseEntity<UserAccount> activateUser(@PathVariable Long chatId) {

        UserAccount userAccount = userAccountService.getByChatId(chatId);

        if (userAccount == null) {
            return ResponseEntity.notFound().build();
        }

        userAccount.setActive(true);
        UserAccount updatedUserAccount = userAccountService.updateUserAccount(userAccount);
        return ResponseEntity.ok(updatedUserAccount);
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long chatId) {
        userAccountService.deleteByChatId(chatId);
        return ResponseEntity.noContent().build();
    }
}


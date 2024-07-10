package com.app.controllers;

import com.app.model.AccessRequest;
import com.app.services.AccessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("request")
public class AccessRequestController {

    private final AccessRequestService accessRequestService;

    @Autowired
    public AccessRequestController(AccessRequestService accessRequestService){
        this.accessRequestService = accessRequestService;
    }

    @GetMapping
    public List<AccessRequest> getAllAccessRequest() {
        return accessRequestService.getAllAccessRequests();
    }
}

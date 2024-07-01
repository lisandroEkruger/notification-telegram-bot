package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.AccessRequest;

@Repository
public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long>{
    public AccessRequest findByChatId(Long chatId);

    public Long deleteByChatId(Long chatId);
}

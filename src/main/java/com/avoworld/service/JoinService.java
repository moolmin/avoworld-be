package com.avoworld.service;

import com.avoworld.dto.JoinDTO;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    public void joinProcess(JoinDTO joinDTO) {

        String email = joinDTO.getEmail();

    }
}

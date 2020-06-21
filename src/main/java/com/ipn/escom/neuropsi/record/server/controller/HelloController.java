package com.ipn.escom.neuropsi.record.server.controller;

import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.record.server.algo.AlgoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private AlgoRepo userRepository;
    
    @GetMapping
    public Map<String, Object> stringObjectMap() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        try {
            List<User> all = userRepository.findAll();
            stringObjectMap.put("users", all);
        } catch (Exception e) {
            e.printStackTrace();
            stringObjectMap.put("error", e.getMessage());
        }
        return stringObjectMap;
    }
}

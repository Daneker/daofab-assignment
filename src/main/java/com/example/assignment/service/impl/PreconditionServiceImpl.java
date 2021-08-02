package com.example.assignment.service.impl;

import com.example.assignment.exception.DefaultException;
import com.example.assignment.service.PreconditionService;
import org.springframework.stereotype.Service;

@Service
public class PreconditionServiceImpl implements PreconditionService {

    @Override
    public <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new DefaultException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }
}

package com.example.assignment.service;

public interface PreconditionService {
    <T> T checkNotNull(T reference, Object errorMessage);
}

package com.example.assignment.controller;

import org.springframework.web.bind.annotation.RequestMapping;

// Generally total count is needed for frontend for accurate pagination
@RequestMapping("/api/v1")
public abstract class V1BaseController {
    protected static final String TOTAL_COUNT = "X-Total-Count";
}

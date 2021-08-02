package com.example.assignment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Child {
    private Long id;
    private String sender;
    private String receiver;
    private Long totalAmount;
    private Long paidAmount;
}

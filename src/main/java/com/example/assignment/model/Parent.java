package com.example.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parent {
    private Long id;
    private String sender;
    private String receiver;
    private Long totalAmount;
//    @JsonIgnore
    private Long totalPaidAmount;
}


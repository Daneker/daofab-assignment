package com.example.assignment.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    public static final Integer DEFAULT_PAGE_NUMBER = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 2;

    @Min(1)
    private Integer page = DEFAULT_PAGE_NUMBER;

    @Min(1)
    private Integer size = DEFAULT_PAGE_SIZE;
}
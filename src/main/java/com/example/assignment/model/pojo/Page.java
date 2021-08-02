package com.example.assignment.model.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
public class Page<T> {
    private List<T> content;
    private Pagination pagination;
    private Long total;

    public Page(Pagination pagination) {
        this.content = Collections.emptyList();
        this.pagination = pagination;
        this.total = 0L;
    }

    public Page(List<T> content, Pagination pagination, Long total) {
        this.content = content;
        this.pagination = pagination;
        this.total = total;
    }
}
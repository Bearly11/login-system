package com.phanith.logintask.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponse<T> {
    private List<T> items;
    private int currentPage;
    private int pageSize;
    private long totalElements;
    private int totalPage;
}

package com.store.pharmacy.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Paging {

    private Long count;

    private Long pageNumber;

    private Long pageSize;

    private Long pageOffset;

    private Long pageTotal;
}

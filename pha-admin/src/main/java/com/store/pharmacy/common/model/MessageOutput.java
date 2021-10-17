package com.store.pharmacy.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageOutput {

    private String messageCode;

    private String text;
}

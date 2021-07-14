package com.store.pharmacy.common.model;

import java.io.Serializable;

public class CommonId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String commonCode;
    private int sequenceNo;

    public CommonId() {
    }

    public CommonId(String commonCode, int sequenceNo) {
        this.commonCode = commonCode;
        this.sequenceNo = sequenceNo;
    }
}

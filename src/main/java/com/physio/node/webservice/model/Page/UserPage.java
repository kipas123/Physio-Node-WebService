package com.physio.node.webservice.model.Page;

import org.springframework.data.domain.Sort;

public class UserPage {
    private int pageNumber = 0;
    private int pageSize = 10;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

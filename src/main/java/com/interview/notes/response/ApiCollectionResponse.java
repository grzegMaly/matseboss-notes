package com.interview.notes.response;

import java.util.Collection;

public class ApiCollectionResponse<T> extends ApiResponse<Collection<T>> {

    private int totalElements;

    public ApiCollectionResponse(String message, Collection<T> data) {
        super(message, data);
        this.totalElements = data.size();
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}

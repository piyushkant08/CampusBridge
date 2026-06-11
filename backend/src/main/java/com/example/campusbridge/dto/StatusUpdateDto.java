package com.example.campusbridge.dto;

import com.example.campusbridge.entity.RequestStatus;
import jakarta.validation.constraints.NotNull;

public class StatusUpdateDto {
    @NotNull(message = "Status is required")
    private RequestStatus status;

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}

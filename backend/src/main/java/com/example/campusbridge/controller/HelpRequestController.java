package com.example.campusbridge.controller;

import com.example.campusbridge.dto.CommentDto;
import com.example.campusbridge.dto.HelpRequestDto;
import com.example.campusbridge.dto.StatusUpdateDto;
import com.example.campusbridge.entity.HelpRequest;
import com.example.campusbridge.entity.RequestCategory;
import com.example.campusbridge.entity.RequestComment;
import com.example.campusbridge.entity.RequestStatus;
import com.example.campusbridge.service.HelpRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "http://localhost:5173")
public class HelpRequestController {
    private final HelpRequestService helpRequestService;

    public HelpRequestController(HelpRequestService helpRequestService) {
        this.helpRequestService = helpRequestService;
    }

    @GetMapping
    public ResponseEntity<List<HelpRequest>> getAllRequests(
            @RequestParam(required = false) RequestStatus status,
            @RequestParam(required = false) RequestCategory category
    ) {
        return ResponseEntity.ok(helpRequestService.getAllRequests(status, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HelpRequest> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(helpRequestService.getRequestById(id));
    }

    @PostMapping
    public ResponseEntity<HelpRequest> createRequest(@Valid @RequestBody HelpRequestDto dto) {
        HelpRequest createdRequest = helpRequestService.createRequest(dto);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HelpRequest> updateRequest(@PathVariable Long id, @Valid @RequestBody HelpRequestDto dto) {
        return ResponseEntity.ok(helpRequestService.updateRequest(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<HelpRequest> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateDto dto) {
        return ResponseEntity.ok(helpRequestService.updateStatus(id, dto.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        helpRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<RequestComment> addComment(@PathVariable Long id, @Valid @RequestBody CommentDto dto) {
        RequestComment comment = helpRequestService.addComment(id, dto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<RequestComment>> getComments(@PathVariable Long id) {
        return ResponseEntity.ok(helpRequestService.getComments(id));
    }
}

package com.example.campusbridge.service;

import com.example.campusbridge.dto.CommentDto;
import com.example.campusbridge.dto.HelpRequestDto;
import com.example.campusbridge.entity.HelpRequest;
import com.example.campusbridge.entity.RequestCategory;
import com.example.campusbridge.entity.RequestComment;
import com.example.campusbridge.entity.RequestStatus;
import com.example.campusbridge.exception.ResourceNotFoundException;
import com.example.campusbridge.repository.HelpRequestRepository;
import com.example.campusbridge.repository.RequestCommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpRequestService {
    private final HelpRequestRepository helpRequestRepository;
    private final RequestCommentRepository requestCommentRepository;

    public HelpRequestService(HelpRequestRepository helpRequestRepository, RequestCommentRepository requestCommentRepository) {
        this.helpRequestRepository = helpRequestRepository;
        this.requestCommentRepository = requestCommentRepository;
    }

    public List<HelpRequest> getAllRequests(RequestStatus status, RequestCategory category) {
        if (status != null && category != null) {
            return helpRequestRepository.findByStatusAndCategoryOrderByCreatedAtDesc(status, category);
        }

        if (status != null) {
            return helpRequestRepository.findByStatusOrderByCreatedAtDesc(status);
        }

        if (category != null) {
            return helpRequestRepository.findByCategoryOrderByCreatedAtDesc(category);
        }

        return helpRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    public HelpRequest getRequestById(Long id) {
        return helpRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Help request not found with id: " + id));
    }

    public HelpRequest createRequest(HelpRequestDto dto) {
        HelpRequest request = new HelpRequest(
                dto.getStudentName(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getCategory(),
                dto.getPriority()
        );

        return helpRequestRepository.save(request);
    }

    public HelpRequest updateRequest(Long id, HelpRequestDto dto) {
        HelpRequest request = getRequestById(id);

        request.setStudentName(dto.getStudentName());
        request.setTitle(dto.getTitle());
        request.setDescription(dto.getDescription());
        request.setCategory(dto.getCategory());
        request.setPriority(dto.getPriority());
        request.setUpdatedAt(LocalDateTime.now());

        return helpRequestRepository.save(request);
    }

    public HelpRequest updateStatus(Long id, RequestStatus status) {
        HelpRequest request = getRequestById(id);
        request.setStatus(status);
        request.setUpdatedAt(LocalDateTime.now());
        return helpRequestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        HelpRequest request = getRequestById(id);
        helpRequestRepository.delete(request);
    }

    public RequestComment addComment(Long requestId, CommentDto dto) {
        HelpRequest request = getRequestById(requestId);
        RequestComment comment = new RequestComment(dto.getAuthorName(), dto.getMessage(), request);

        if (request.getStatus() == RequestStatus.OPEN) {
            request.setStatus(RequestStatus.IN_PROGRESS);
            request.setUpdatedAt(LocalDateTime.now());
            helpRequestRepository.save(request);
        }

        return requestCommentRepository.save(comment);
    }

    public List<RequestComment> getComments(Long requestId) {
        getRequestById(requestId);
        return requestCommentRepository.findByHelpRequestIdOrderByCreatedAtDesc(requestId);
    }
}

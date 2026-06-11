package com.example.campusbridge.repository;

import com.example.campusbridge.entity.HelpRequest;
import com.example.campusbridge.entity.RequestCategory;
import com.example.campusbridge.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpRequestRepository extends JpaRepository<HelpRequest, Long> {
    List<HelpRequest> findByStatusOrderByCreatedAtDesc(RequestStatus status);
    List<HelpRequest> findByCategoryOrderByCreatedAtDesc(RequestCategory category);
    List<HelpRequest> findByStatusAndCategoryOrderByCreatedAtDesc(RequestStatus status, RequestCategory category);
    List<HelpRequest> findAllByOrderByCreatedAtDesc();
}

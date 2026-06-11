package com.example.campusbridge.repository;

import com.example.campusbridge.entity.RequestComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestCommentRepository extends JpaRepository<RequestComment, Long> {
    List<RequestComment> findByHelpRequestIdOrderByCreatedAtDesc(Long helpRequestId);
}

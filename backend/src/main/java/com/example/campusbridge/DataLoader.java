package com.example.campusbridge;

import com.example.campusbridge.entity.HelpRequest;
import com.example.campusbridge.entity.Priority;
import com.example.campusbridge.entity.RequestCategory;
import com.example.campusbridge.entity.RequestComment;
import com.example.campusbridge.entity.RequestStatus;
import com.example.campusbridge.repository.HelpRequestRepository;
import com.example.campusbridge.repository.RequestCommentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final HelpRequestRepository helpRequestRepository;
    private final RequestCommentRepository requestCommentRepository;

    public DataLoader(HelpRequestRepository helpRequestRepository, RequestCommentRepository requestCommentRepository) {
        this.helpRequestRepository = helpRequestRepository;
        this.requestCommentRepository = requestCommentRepository;
    }

    @Override
    public void run(String... args) {
        if (helpRequestRepository.count() > 0) {
            return;
        }

        HelpRequest request1 = new HelpRequest(
                "Aarav",
                "Need DBMS notes for normalization",
                "I missed the class on 2NF and 3NF. Can someone share short notes or explain with examples?",
                RequestCategory.ACADEMICS,
                Priority.MEDIUM
        );

        HelpRequest request2 = new HelpRequest(
                "Riya",
                "Looking for teammate for Java mini project",
                "I know frontend basics but need someone who can help with Spring Boot backend.",
                RequestCategory.PROJECTS,
                Priority.HIGH
        );

        HelpRequest request3 = new HelpRequest(
                "Kabir",
                "Lost calculator near library",
                "Black Casio calculator lost around 5 PM near the reading hall.",
                RequestCategory.LOST_FOUND,
                Priority.LOW
        );

        request2.setStatus(RequestStatus.IN_PROGRESS);

        helpRequestRepository.save(request1);
        helpRequestRepository.save(request2);
        helpRequestRepository.save(request3);

        RequestComment comment = new RequestComment(
                "Mentor Shivam",
                "Start with Controller, Service, Repository. I can share a simple boilerplate.",
                request2
        );
        requestCommentRepository.save(comment);
    }
}

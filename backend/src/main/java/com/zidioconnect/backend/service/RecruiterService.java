package com.zidioconnect.backend.service;

import com.zidioconnect.backend.model.Recruiter;
import com.zidioconnect.backend.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepo;

    public Recruiter getRecruiterByEmail(String email){
        return recruiterRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Recruiter not found"));
    }

    public String saveorUpdateRecruiter(Recruiter recruiter){
        recruiterRepo.save(recruiter);
        return "Recruiter saved/updated!";
    }
}

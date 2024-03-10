package com.atlas.firstjobapp.job.impl;

import com.atlas.firstjobapp.job.Job;
import com.atlas.firstjobapp.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobServicesImplementation implements JobServices {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepo;

    public JobServicesImplementation(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public List<Job> findAll() {
        return jobRepo.findAll();
    }

    @Override
    public void addJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public Job findJobID(Long id){
//        for (Job job: jobs) {
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;

        return jobRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobID(Long id){
//        for(Job job: jobs){
//            if (job.getId().equals(id)){
//                jobs.remove(job);
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob){
        Optional<Job> optionalJob = jobRepo.findById(id);

        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            job.setCompany(updatedJob.getCompany());
            jobRepo.save(job);
            return true;
        }
        return false;
    }
}

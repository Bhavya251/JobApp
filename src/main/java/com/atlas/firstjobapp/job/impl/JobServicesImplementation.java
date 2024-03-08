package com.atlas.firstjobapp.job.impl;

import com.atlas.firstjobapp.job.Job;
import com.atlas.firstjobapp.job.JobServices;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobServicesImplementation implements JobServices {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void addJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job findJobID(Long id){
        for (Job job: jobs) {
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobID(Long id){
        for(Job job: jobs){
            if (job.getId().equals(id)){
                jobs.remove(job);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob){
        for (Job job: jobs){
            if(job.getId().equals(id)){
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
}

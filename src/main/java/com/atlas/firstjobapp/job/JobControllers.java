package com.atlas.firstjobapp.job;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
GET /jobs: Get all jobs
GET /jobs/{id}: Get a specific job by ID
POST /jobs: Create a new job (request body should contain the job details)
DELETE /jobs/{id}: Delete a specific job by ID
PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated job
GET /jobs/{id}/company: Get the company associated with a specific job by ID

Example API URLs:
GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1
GET {base_url}/jobs/1/company

 */


@RestController
public class JobControllers {
    private JobServices jobServices;

    public JobControllers(JobServices jobServices) {
        this.jobServices = jobServices;
    }

    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobServices.findAll();
    }

    @PostMapping("/jobs")
    public String addJob(@RequestBody Job job){
        jobServices.addJob(job);
        return "Job Added";
    }

    @GetMapping("/jobs/{id}")
    public Job findJobID(@PathVariable Long id){
        Job job = jobServices.findJobID(id);
        if(job == null){
            return new Job(id, "No Job posting available", "No Job posting available with this ID", "NA", "NA", "NA");
        }
        return job;
    }
}
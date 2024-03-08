package com.atlas.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

/**
GET /jobs: Get all jobs
GET /jobs/{id}: Get a specific job by ID
POST /jobs: Create a new job (request body should contain the job details)
DELETE /jobs/{id}: Delete a specific job by ID
PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated job)
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
@RequestMapping("/jobs")
public class JobControllers {
    private JobServices jobServices;

    public JobControllers(JobServices jobServices) {
        this.jobServices = jobServices;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobServices.findAll());
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job){
        jobServices.addJob(job);
        //return new ResponseEntity<>("Job added", HttpStatus.CREATED);
        return ResponseEntity.ok("Job Added");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobID(@PathVariable Long id){
        //ResponseEntity
        Job job = jobServices.findJobID(id);
        if(job == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJobID(@PathVariable Long id){
        //ResponseEntity
        boolean deleteStatus = jobServices.deleteJobID(id);
        if(!deleteStatus){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted Job ID: "+id, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updateStatus = jobServices.updateJob(id, updatedJob);
        if (!updateStatus)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Job Updated", HttpStatus.OK);
    }
}
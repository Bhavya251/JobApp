package com.atlas.firstjobapp.job;

import com.atlas.firstjobapp.company.Company;
import com.atlas.firstjobapp.job.impl.JobServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        System.out.println("1. "+job.getId());
        System.out.println("2. "+job.getTitle());
        System.out.println("3. "+job.getDescription());
        System.out.println("4. "+job.getLocation());
        System.out.println("5. "+job.getMinSalary());
        System.out.println("6. "+job.getMaxSalary());
        System.out.println("7. "+job.getCompany().getCompID());
        System.out.println("8. "+job.getCompany().getName());
        System.out.println("9. "+job.getCompany().getCompDescription());
        System.out.println("0. "+job.getCompany().getJobs());

        return ResponseEntity.ok("New Job Added");
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
        return new ResponseEntity<>("Deleted Job with ID: "+id, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updateStatus = jobServices.updateJob(id, updatedJob);
        if (!updateStatus)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Job Details Updated", HttpStatus.OK);
    }
}
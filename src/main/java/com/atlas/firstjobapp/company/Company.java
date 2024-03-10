package com.atlas.firstjobapp.company;

import com.atlas.firstjobapp.job.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto-generate ID value
    private Long compID;
    private String name;
    private String compDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL) //Mapping: One Company -> Many Jobs
    private List<Job> jobs;

    public Company() {
    }

    public Long getCompID() {
        return compID;
    }

    public void setCompID(Long compID) {
        this.compID = compID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompDescription() {
        return compDescription;
    }

    public void setCompDescription(String compDescription) {
        this.compDescription = compDescription;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}

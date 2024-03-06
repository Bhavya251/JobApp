package com.atlas.firstjobapp.job;

import java.util.List;

public interface JobServices {
    List<Job> findAll();
    void addJob(Job job);
    Job findJobID(Long id);
}

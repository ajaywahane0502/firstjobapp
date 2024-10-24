package com.jobapp.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class jobController {

  private JobService jobService;

    public jobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job> >findAll(){

        return  ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){

        jobService.createJob(job);
        return  new ResponseEntity<>("JObs added Succesfully",HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id)
    {

    Job job= jobService.getJobById(id);
    System.out.println(job);
    if(job !=null){
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deletejob(@PathVariable Long id)
    {
        boolean deleted=jobService.deleteByJobId(id);

        if(deleted)
            return new ResponseEntity<>("Jobs deleted Succesfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob)
    {
        boolean updated=jobService.updateJob(id,updatedJob);
        if (updated)
            return new ResponseEntity<>("Job Updated Succesfullly",HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

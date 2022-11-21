package vn.techmaster.personalmanagementcrud.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.personalmanagementcrud.model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JobRepository {
    private List<Job> jobs = new ArrayList<>();

    public JobRepository() {
        List<Job> list = List.of(
                new Job(1, "Developer"),
                new Job(2, "Doctor"),
                new Job(3, "Teacher")
        );
        for (Job job : list) {
            jobs.add(job);
        }
    }

    public Optional<Job> get(int id) {
        return jobs.stream().filter(c -> c.getId()==id).findFirst();
    }

    public List<Job> getAllJobs() {
        return this.jobs;
    }

    public Job create(Job job) {
        int id;
        if (jobs.isEmpty()) {
            id = 1;
        } else {
            Job lastJob = jobs.get(jobs.size()-1);
            id = lastJob.getId() + 1;
        }
        job.setId(id);
        jobs.add(job);
        return job;
    }

    public Job edit(Job job) {
        get(job.getId()).ifPresent(existJob -> {
            existJob.setName(job.getName());
        });
        return job;
    }

    public void deleteById(int id) {
        get(id).ifPresent(exist -> jobs.remove(exist));
    }

    public void delete(Job job) {
        deleteById(job.getId());
    }

    public Job search(String name) {
        return jobs.stream().filter(i -> i.getName().contains(name)).findAny().orElse(null);
    }
}

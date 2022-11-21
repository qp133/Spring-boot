package vn.techmaster.personalmanagementcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.personalmanagementcrud.model.Job;
import vn.techmaster.personalmanagementcrud.repository.JobRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class JobController {
    @Autowired
    private JobRepository jobRepo;

    @GetMapping("/listAllJobs")
    public String getAllJob(Model model){
        model.addAttribute("jobs",jobRepo.getAllJobs());
        return "jobList";
    }

    //Job Form
    @GetMapping("/addJob")
    public String addJob(Model model){
        model.addAttribute("job",new Job());
        return "jobForm";
    }

    //Them job
    @PostMapping("/addJob")
    public String addJob(@Valid @ModelAttribute("job")Job job, BindingResult result, Model model){

        if(result.hasErrors()){
            return "jobForm";
        }
        if (job.getId() > 0) {
            jobRepo.edit(job);
            model.addAttribute("jobs",jobRepo.getAllJobs());
            return "jobList";
        } else {
            Job newJob = jobRepo.create(job);
            if(newJob!=null){
                model.addAttribute("jobs",jobRepo.getAllJobs());
                return "jobList";
            }
            model.addAttribute("error","Job already exist");
            return "jobForm";
        }

    }

    //Cap nhat Job
    @GetMapping("/job/edit/{id}")
    public String editJob(@PathVariable("id") int id, Model model) {
        Optional<Job> job = jobRepo.get(id);
        if (job.isPresent()) {
            model.addAttribute("job", job.get());
        }
        return "jobForm";
    }

    //Xoa job
    @GetMapping("/job/delete/{id}")
    public String deleteJob(@PathVariable("id") int id, Model model) {
        jobRepo.deleteById(id);
        model.addAttribute("jobs", jobRepo.getAllJobs());
        return "jobList";
    }
}

package batch.process.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecutorJob {

	@Autowired
    private Job job;
 
    @Autowired
    private JobLauncher jobLauncher;
    
 
    public JobExecution execute(String[] args) throws Exception {
    	
        JobParameters jobParameters = new JobParametersBuilder()
        		.addString("JobID", String.valueOf(System.currentTimeMillis()))
        		.addString("path.file.name", args.length>0 ? args[0] : null)
                .toJobParameters();
        return jobLauncher.run(job,jobParameters);
    }
}

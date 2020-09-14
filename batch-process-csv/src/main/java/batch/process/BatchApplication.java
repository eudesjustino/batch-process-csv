package batch.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import batch.process.job.ExecutorJob;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BatchApplication implements CommandLineRunner{

	@Autowired
    private ExecutorJob executorJob;
	
	public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
	
	@Override
	public void run(String... args) throws Exception {
		executorJob.execute(args);
		
	}

}

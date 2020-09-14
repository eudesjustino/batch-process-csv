package batch.process.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepListener implements StepExecutionListener {
	
	Logger  logger = LoggerFactory.getLogger(StepListener.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("Iniciando Step: "+stepExecution.getStepName());		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.info("Finalizando Step: "+stepExecution.getStepName());	
		return ExitStatus.COMPLETED;
	}

}

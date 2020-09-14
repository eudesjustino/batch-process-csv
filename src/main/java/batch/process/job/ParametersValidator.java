package batch.process.job;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;


public class ParametersValidator implements JobParametersValidator {

	private static final String KEY_PATH_FILE = "path.file.name";

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
			
		String fileName = parameters.getString(KEY_PATH_FILE);
		
		if(fileName==null || fileName.isEmpty()) {
			throw new JobParametersInvalidException("arquivo para processamento é obrigatorio para o job");
		}
		
		if(!Files.exists(Paths.get(fileName), LinkOption.NOFOLLOW_LINKS)) {
			throw new JobParametersInvalidException("Arquivo '" + fileName + "' Arquivo não existe");
			
		}		
		
	}

}

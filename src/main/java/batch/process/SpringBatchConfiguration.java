package batch.process;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import batch.process.dominio.Conta;
import batch.process.job.ContaItemProcessor;
import batch.process.job.ContaItemWriter;
import batch.process.job.ContaReader;
import batch.process.job.JobListener;
import batch.process.job.ParametersValidator;
import batch.process.job.StepItemProcessListener;
import batch.process.job.StepItemReadListener;
import batch.process.job.StepItemWriteListener;
import batch.process.job.StepListener;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job jobConta() {
		return jobBuilderFactory
				.get("jobConta")
				.validator(validador())
				.listener(new JobListener())
				.incrementer(new RunIdIncrementer())
				.start(atualizarContas())
				.build();
	}


	@Bean
	public Step atualizarContas() {
		return stepBuilderFactory
					.get("atualizarContas")
					.<Conta, Conta>chunk(10)
					.listener(new StepItemReadListener())
					.reader(reader(null))
					.processor(processor())
					.listener(new StepItemProcessListener())
					.writer(writer(null))
					.listener(new StepItemWriteListener())
					.listener(new StepListener())
					.build();
	}

	@Bean
	public JobParametersValidator validador() {
		return new ParametersValidator();
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<Conta> reader(@Value("#{jobParameters['path.file.name']}") String fileName) {
		return new ContaReader(fileName);
	}
	
	@Bean
	public ItemProcessor<Conta, Conta> processor() {
		return new ContaItemProcessor();
	}

	@Bean
	@StepScope
	public FlatFileItemWriter<Conta> writer(@Value("#{jobParameters['path.file.name']}") String fileName) {
		return new ContaItemWriter(fileName);
	}
	
}

package batch.process.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import batch.process.dominio.Conta;

public class StepItemWriteListener implements ItemWriteListener<Conta>{

	Logger  logger = LoggerFactory.getLogger(StepItemWriteListener.class);
	
	@Override
	public void beforeWrite(List<? extends Conta> contas) {
		logger.info("Iniciando a escrita das contas do arquivo output.csv : "+contas.toString());
		
	}

	@Override
	public void afterWrite(List<? extends Conta> contas) {
		logger.info("contas salva no arquivo");
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Conta> contas) {
		logger.info("Erro ao salvar dados no arquivo: "+exception.getMessage());
		
	}

}

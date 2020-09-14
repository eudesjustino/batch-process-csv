package batch.process.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import batch.process.dominio.Conta;

public class StepItemReadListener implements ItemReadListener<Conta>{

	Logger  logger = LoggerFactory.getLogger(StepItemReadListener.class);
	
	@Override
	public void beforeRead() {
		logger.info("Iniciando a Leitura do Arquivo...");
		
	}

	@Override
	public void afterRead(Conta conta) {
		logger.info("Conta : "+conta.toString());
		
	}

	@Override
	public void onReadError(Exception ex) {
		logger.info("Erro ao fazer a Leitua: "+ex.getMessage());
		
	}

}

package batch.process.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

import batch.process.dominio.Conta;

public class StepItemProcessListener implements ItemProcessListener<Conta, Conta>{

	Logger  logger = LoggerFactory.getLogger(StepItemProcessListener.class);
	
	@Override
	public void beforeProcess(Conta conta) {
		logger.info("Iniciando o processamento da conta "+conta.toString());
		
	}

	@Override
	public void afterProcess(Conta item, Conta result) {
		logger.info("Conta processada: "+result.toString());
		
	}

	@Override
	public void onProcessError(Conta item, Exception e) {
		logger.info("Erro ao processa a conta "+item.toString()+" : "+e.getMessage());
		
	}

}

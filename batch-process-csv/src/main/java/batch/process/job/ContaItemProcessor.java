package batch.process.job;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import batch.process.dominio.Conta;
import batch.process.service.ReceitaService;


public class ContaItemProcessor implements  ItemProcessor<Conta, Conta>{
	
	@Autowired
	private ReceitaService service;

	@Override
	public Conta process(Conta conta) throws Exception {
		
		String agencia = conta.getAgencia();
		agencia = agencia.replaceAll("[^0-9]", "");
		String numeroConta = conta.getConta();
		numeroConta = numeroConta.replaceAll("[^0-9]", "");
		Double saldo = conta.getSaldo();
		String status = conta.getStatus();
		
		try {
			
			boolean contaAtualizada = service.atualizarConta(agencia, numeroConta, saldo, status);
			
			if(contaAtualizada) {
				conta.setProcessado(true);
			}else {
				conta.setProcessado(false);
			}
		} catch (Exception e) {
			conta.setProcessado(false);
		}
		
		return conta;
	}

}

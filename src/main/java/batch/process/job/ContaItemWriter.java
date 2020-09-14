package batch.process.job;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import batch.process.dominio.Conta;


public class ContaItemWriter extends FlatFileItemWriter<Conta>{
	
	private static final String STR = "\\";
	private static final String FILE_OUTPUT = "output.csv";
	String cabecalho[] = new String[] { "agencia", "conta", "saldo","status","processado" };
	
	
	public ContaItemWriter(String fileName) {
		
		String diretorio = Paths.get(fileName).getParent().toString();
		diretorio = diretorio.concat(STR).concat(FILE_OUTPUT);
		
		setResource(new FileSystemResource(diretorio));
		setAppendAllowed(true);
		setLineAggregator(new DelimitedLineAggregator<Conta>() {
            {
                setDelimiter(";");
                setFieldExtractor(new BeanWrapperFieldExtractor<Conta>() {
                    {
                        setNames(cabecalho);
                    }
                });
            }
        });
		
		setHeaderCallback(new FlatFileHeaderCallback() {

	        public void writeHeader(Writer writer) throws IOException {
	                writer.write(gerarCabecalho());
	        }

			private String gerarCabecalho() {
				Stream<String> streamCabecalho = Arrays.stream(cabecalho);
	        	String camposConcatenados = streamCabecalho.map(Object::toString).collect(Collectors.joining(";"));
				return camposConcatenados;
			}
	    });
	}
	
}

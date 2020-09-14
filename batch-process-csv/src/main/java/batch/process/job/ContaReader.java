package batch.process.job;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.io.FileSystemResource;

import batch.process.dominio.Conta;


public class ContaReader extends FlatFileItemReader<Conta> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ContaReader(String fileName) {
		setResource(new FileSystemResource(fileName));
		setLinesToSkip(1);
		setLineMapper(new DefaultLineMapper() {
			{
				// 4 colunas em cada linha
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "agencia", "conta", "saldo", "status" });
						// setIncludedFields(new int[] { 0, 1, 2, 3 });
						setDelimiter(";");

					}
				});
				// Definir valores na classe "Conta"
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Conta>() {
					{
						setTargetType(Conta.class);
						// Map<Class<String>, PropertyEditor> customEditors = new HashMap<Class<String>,
						// PropertyEditor>();
						// setCustomEditors(customEditors);
						setConversionService(new ConversionService() {

							@Override
							public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
								return Double.valueOf(source.toString().replace(',', '.'));
							}

							@Override
							public <T> T convert(Object source, Class<T> targetType) {
								return (T) Double.valueOf(source.toString().replace(',', '.'));
							}

							@Override
							public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
								return sourceType.equals(TypeDescriptor.valueOf(String.class))
										&& targetType.equals(TypeDescriptor.valueOf(double.class));
							}

							@Override
							public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
								return sourceType == String.class && targetType == double.class;
							}
						});

					}

				});
			}
		});
	}
}

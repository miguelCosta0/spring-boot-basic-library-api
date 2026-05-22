package library.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import library.valueObject.Cpf;

@Converter(autoApply = true)
public class CpfConverter implements AttributeConverter<Cpf, String> {

    @Override
    public String convertToDatabaseColumn(Cpf cpf) {
        return cpf != null ? cpf.getRawCpf() : null;
    }

    @Override
    public Cpf convertToEntityAttribute(String dbData) {
        return dbData != null ? new Cpf(dbData) : null;
    }
}

package br.com.loris.culturalapi.enums.converter;

import br.com.loris.culturalapi.enums.CulturalProductType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CulturalProductTypeConverter implements AttributeConverter<CulturalProductType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CulturalProductType attribute) {
        return attribute.getValue();
    }

    @Override
    public CulturalProductType convertToEntityAttribute(Integer dbValue) {
        for (CulturalProductType nodeType : CulturalProductType.values()) {
            if (nodeType.getValue().equals(dbValue)) {
                return nodeType;
            }
        }
        return CulturalProductType.UNDEFINED;
    }
}

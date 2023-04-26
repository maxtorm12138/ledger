package org.maxtorm.ledger.entity.commodity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CommodityAttributeConverter implements AttributeConverter<Commodity, String> {
    @Override
    public String convertToDatabaseColumn(Commodity commodity) {
        return commodity.getQualifiedName();
    }

    @Override
    public Commodity convertToEntityAttribute(String qualifiedName) {
        return CommodityBuilder.fromQualifiedName(qualifiedName);
    }
}

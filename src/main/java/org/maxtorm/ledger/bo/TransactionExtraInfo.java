package org.maxtorm.ledger.bo;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.helpers.MessageFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Getter
@Setter
public class TransactionExtraInfo {
    @Converter(autoApply = true)
    public static class TransactionExtraInfoConverter implements AttributeConverter<TransactionExtraInfo, String> {
      @Override
      public String convertToDatabaseColumn(TransactionExtraInfo transactionExtraInfo) {
          try {
              return new ObjectMapper().writeValueAsString(transactionExtraInfo);
          } catch (JsonProcessingException e) {
              throw new RuntimeException(MessageFormatter.format("Error converting TransactionExtraInfo: {}", transactionExtraInfo).getMessage());
          }
      }

      @Override
      public TransactionExtraInfo convertToEntityAttribute(String strTransactionExtraInfo) {
          try {
              return new ObjectMapper().readValue(strTransactionExtraInfo, TransactionExtraInfo.class);
          } catch (JsonProcessingException e) {
              throw new RuntimeException(MessageFormatter.format("Error converting TransactionExtraInfo: {}", strTransactionExtraInfo).getMessage());
          }
      }
    }


}

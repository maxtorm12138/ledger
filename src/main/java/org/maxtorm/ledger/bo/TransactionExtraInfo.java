package org.maxtorm.ledger.bo;

import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.slf4j.helpers.MessageFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Getter
@Setter
@ToString
public class TransactionExtraInfo {
    @Converter(autoApply = true)
    public static class TransactionExtraInfoConverter implements AttributeConverter<TransactionExtraInfo, String> {
      @Override
      public String convertToDatabaseColumn(TransactionExtraInfo transactionExtraInfo) {
          try {
              return new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).writeValueAsString(transactionExtraInfo);
          } catch (JsonProcessingException e) {
              throw new RuntimeException(MessageFormatter.format("Error converting TransactionExtraInfo: {}", e.getMessage()).getMessage());
          }
      }

      @Override
      public TransactionExtraInfo convertToEntityAttribute(String strTransactionExtraInfo) {
          try {
              return new ObjectMapper().readValue(strTransactionExtraInfo, TransactionExtraInfo.class);
          } catch (JsonProcessingException e) {
              throw new RuntimeException(MessageFormatter.format("Error converting TransactionExtraInfo: {}", e.getMessage()).getMessage());
          }
      }
    }


}

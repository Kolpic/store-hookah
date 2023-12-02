package com.cloud.rebellion.demo.helper;

import com.cloud.rebellion.demo.exception.ExceededBlobLengthException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobDeserializer extends JsonDeserializer<Blob> {

    @SneakyThrows(SQLException.class)
    @Override
    public Blob deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String base64Encoded = jsonParser.getValueAsString();
        byte[] bytes = Base64.getDecoder().decode(base64Encoded);
        return new javax.sql.rowset.serial.SerialBlob(bytes);
    }
}

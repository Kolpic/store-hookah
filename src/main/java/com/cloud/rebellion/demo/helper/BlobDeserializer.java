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

/**
 * Custom deserializer for Blob objects.
 * This deserializer is used to convert a base64 encoded string into a SQL Blob type.
 * It is typically used in the context of Jackson's deserialization process.
 */
public class BlobDeserializer extends JsonDeserializer<Blob> {

    /**
     * Deserializes a JSON-encoded base64 string to a SQL Blob.
     *
     * @param jsonParser The JsonParser providing access to the JSON content being processed.
     * @param deserializationContext The context for the process of deserialization.
     * @return The deserialized Blob object.
     * @throws IOException If an input/output error occurs.
     * @throws SQLException If an SQL error occurs during Blob creation.
     */
    @SneakyThrows(SQLException.class)
    @Override
    public Blob deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String base64Encoded = jsonParser.getValueAsString();
        byte[] bytes = Base64.getDecoder().decode(base64Encoded);
        return new javax.sql.rowset.serial.SerialBlob(bytes);
    }
}

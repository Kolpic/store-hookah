package com.cloud.rebellion.demo.helper;

import com.cloud.rebellion.demo.exception.ExceededBlobLengthException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Custom serializer for Blob objects.
 * This serializer is used to convert SQL Blob types into a JSON binary format.
 * It is typically used in the context of Jackson's serialization process.
 */
public class BlobSerializer extends JsonSerializer<Blob> {

    /**
     * Serializes a SQL Blob to a JSON binary format.
     *
     * @param blob The Blob object to be serialized.
     * @param jsonGenerator The JsonGenerator used to write the JSON content.
     * @param serializerProvider The provider that can be used to get serializers for serializing
     *                           Objects value contains, if any.
     * @throws IOException If an input/output error occurs.
     * @throws ExceededBlobLengthException If the Blob size exceeds the allowable limit.
     */
    @SneakyThrows
    @Override
    public void serialize(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Convert Blob to byte array
        byte[] bytes = new byte[0];
        try {
            bytes = blob.getBytes(1, (int) blob.length());
        } catch (SQLException e) {
            throw new ExceededBlobLengthException("Too big picture, cannot be converted");
        }
        jsonGenerator.writeBinary(bytes);
    }
}

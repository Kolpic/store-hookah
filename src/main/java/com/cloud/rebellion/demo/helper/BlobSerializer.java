package com.cloud.rebellion.demo.helper;

import com.cloud.rebellion.demo.exception.ExceededBlobLengthException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobSerializer extends JsonSerializer<Blob> {

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

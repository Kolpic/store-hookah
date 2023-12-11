package com.cloud.rebellion.demo.helper;

import com.cloud.rebellion.demo.exception.ExceededBlobLengthException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlobSerializerTest {

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;
    @InjectMocks
    private BlobSerializer blobSerializer;


    @Test
    public void testSerializeShouldSerializeSuccessful() throws SQLException, IOException {
        // Arrange
        Blob mockBlob = mock(Blob.class);
        when(mockBlob.length()).thenReturn(3L);
        when(mockBlob.getBytes(1, 3)).thenReturn(new byte[]{1, 2, 3});

        // Act
        blobSerializer.serialize(mockBlob, jsonGenerator, serializerProvider);

        // Assert
        // Verify
        verify(jsonGenerator, times(1)).writeBinary(new byte[]{1, 2, 3});
    }

    @Test
    public void testSerializeShouldThrowExceptionWhenLengthPictureIsTooBig() throws SQLException, IOException {
        // Arrange
        Blob mockBlob = mock(Blob.class);
        when(mockBlob.length()).thenReturn(3L);
        when(mockBlob.getBytes(1, 3))
                .thenThrow(new ExceededBlobLengthException("Too big picture, cannot be converted"));

        // Act
        // Assert
        assertThrows(ExceededBlobLengthException.class, () ->
                blobSerializer.serialize(mockBlob, jsonGenerator, serializerProvider),
                "Too big picture, cannot be converted");

        // Verify
        verify(mockBlob, times(1)).getBytes(1,3);
        verify(jsonGenerator, never()).writeBinary(any());
    }
}
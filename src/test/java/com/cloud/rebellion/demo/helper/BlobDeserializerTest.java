package com.cloud.rebellion.demo.helper;

import com.cloud.rebellion.demo.exception.ExceededBlobLengthException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlobDeserializerTest {

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    @InjectMocks
    private BlobDeserializer blobDeserializer;

    @Test
    public void testDeserializeShouldDeserializeSuccessful() throws IOException, SQLException {
        // Arrange
        String imageStringBase64Encoded = "vev";

        when(jsonParser.getValueAsString()).thenReturn(imageStringBase64Encoded);
        // Act
        Blob result = blobDeserializer.deserialize(jsonParser, deserializationContext);

        // Assert
        assertNotNull(result);

        // Verify
        verify(jsonParser, times(1)).getValueAsString();
    }

    @Test
    public void testDeserializeWithInvalidBase64() throws IOException {
        // Arrange
        String invalidBase64 = "vev_";

        when(jsonParser.getValueAsString()).thenReturn(invalidBase64);
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                blobDeserializer.deserialize(jsonParser, deserializationContext));
    }
}
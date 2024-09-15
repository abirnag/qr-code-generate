package com.abirnag.qrcode_generator.handlers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class QRGenerateHandler {
    @Value("${qr.tempDir}")
    private String tempDir;

    public byte[] generateQRCode(String data, int width, int height) {
        String filePath = tempDir+UUID.randomUUID().toString() + ".png";
        try{
            Path path = new File(filePath).toPath();
            log.info("Writing file in filePath: {}", filePath);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(data.getBytes(Charset.defaultCharset()), Charset.defaultCharset()),
                    BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(matrix,"png",path);
            byte[] rawData =  Files.readAllBytes(path);
            Files.delete(new File(filePath).toPath());
            return rawData;
        } catch (WriterException | IOException e) {
            log.error("File Generation Error", e);
            return null;
        }
    }

}

package com.birdRun543.code0.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hanbing
 */
public class QrCodeUtil {

    public static BufferedImage getQrCode(String content)
            throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


        Map<EncodeHintType, Object> hints = new HashMap<>(3);

        //设置二维码四周白色区域的大小

        hints.put(EncodeHintType.MARGIN, 1);
        //设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //画二维码
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

        return image;
    }
}

package wangzhongqiu.spring.springmvc.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 二维码工具类
 */
public class QRCodeUtil {

    public static final int WEIGHT = 173;
    public static final int HEIGHT = 173;

    /**
     * 创建二维码
     *
     * @param content
     * @param outputStream
     */
    public static void create(String content, OutputStream outputStream) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, WEIGHT, HEIGHT);
            // 去白边
            int[] rec = matrix.getEnclosingRectangle();
            int resWidth = rec[2] + 1;
            int resHeight = rec[3] + 1;
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
            resMatrix.clear();
            for (int i = 0; i < resWidth; i++) {
                for (int j = 0; j < resHeight; j++) {
                    if (matrix.get(i + rec[0], j + rec[1])) {
                        resMatrix.set(i, j);
                    }
                }
            }
            MatrixToImageWriter.writeToStream(resMatrix, "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建二维码
     *
     * @param content
     * @param outputFile
     */
    public static void create(String content, File outputFile) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, WEIGHT, HEIGHT);
            MatrixToImageWriter.writeToFile(matrix, "png", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取二维码
     *
     * @param file
     */
    public static String read(File file) {
        try {
            QRCodeReader reader = new QRCodeReader();
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);
            Result result = reader.decode(imageBinaryBitmap);
            System.out.println("qrcode = " + new String(result.getText().getBytes("UTF-8")));
            return new String(result.getText().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取二维码
     *
     * @param inputStream
     */
    public static String read(InputStream inputStream) {
        try {
            QRCodeReader reader = new QRCodeReader();
            BufferedImage image = ImageIO.read(inputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);
            Result result = reader.decode(imageBinaryBitmap);
            System.out.println("qrcode = " + new String(result.getText().getBytes("UTF-8")));
            return new String(result.getText().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String imagePath = "C:\\image\\anqi.png";
        File outputFile = new File(imagePath);
        QRCodeUtil.create(
                new String(
                        "https://www.baidu.com/"
                                .getBytes("UTF-8"), "iso-8859-1"), outputFile);
        QRCodeUtil.read(outputFile);
    }
}

package fhv.team11.project.ems.commons.qrcode;
import net.glxn.qrgen.javase.QRCode;

import java.util.Base64;

public class QRCodeGenerator {

    public static String generateQRCodeImage(String URL, String Token, int width, int height) {
        String QRCodeURL = URL + "?token=" + Token;
        String qrCodeBase64 = Base64.getEncoder().encodeToString(QRCode
                                                                .from(QRCodeURL)
                                                                .withSize(width,height)
                                                                .stream()
                                                                .toByteArray());

        return qrCodeBase64;
    }
}

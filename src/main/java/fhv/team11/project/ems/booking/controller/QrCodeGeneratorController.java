package fhv.team11.project.ems.booking.controller;

import fhv.team11.project.ems.booking.service.BookingService;
import fhv.team11.project.ems.commons.qrcode.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class QrCodeGeneratorController {

    private final BookingService bookingService;
    @Value("server.address")
    private String serverAddress;

    @Autowired
    public QrCodeGeneratorController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/api/bookings/{id}/qr")
    public ResponseEntity<Map<String, String>> generateQrCodeForBooking(@PathVariable("id") Long bookingId) {
        String token = bookingService.getIdentifier(bookingId);
        //TODO: obtain server address from application.properties
        String qrData = "localhost:8080/bookings/checkin" + "?token=" + token;

        Map<String, String> response = new HashMap<>();
        response.put("qrData", qrData);

        return ResponseEntity.ok(response);
    }
}

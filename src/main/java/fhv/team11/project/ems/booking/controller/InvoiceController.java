package fhv.team11.project.ems.booking.controller;

import fhv.team11.project.ems.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InvoiceController {


    private final BookingService bookingService;

    public InvoiceController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //TODO: add some protection to endpoint so that only authorized requests can be made
    @GetMapping("/api/invoice/pay")
    public ResponseEntity<Map<String, String>> payInvoice(@RequestParam("identifier") String identifier) {
        try {
            bookingService.payInvoice(identifier, null);
            return ResponseEntity.ok(Map.of("message", "success"));
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}

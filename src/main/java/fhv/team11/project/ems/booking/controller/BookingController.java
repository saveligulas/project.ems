package fhv.team11.project.ems.booking.controller;

import fhv.team11.project.ems.booking.service.BookingService;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.qrcode.QRCodeGenerator;
import fhv.team11.project.ems.commons.validation.ValidationExceptionToBindingResultFactory;
import fhv.team11.project.ems.commons.validation.error.SimpleValidationException;
import fhv.team11.project.ems.domain.booking.InvoiceDelivery;
import fhv.team11.project.ems.domain.booking.PaymentMethod;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.service.ActiveEventService;
import fhv.team11.project.ems.events.service.EventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.permission.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookingController {
    private final BookingService bookingService;
    private final ActiveEventService activeEventService;

    @Autowired
    public BookingController(BookingService bookingService, ActiveEventService activeEventService, EventTemplateService eventTemplateService) {
        this.bookingService = bookingService;
        this.activeEventService = activeEventService;
    }

    @ModelAttribute("createBooking")
    public CreateBookingDTO createBookingDTO() {
        return new CreateBookingDTO();
    }

    @GetMapping("/active-events/{id}/booking")
    public ModelAndView getBookingForm(@PathVariable("id") Long id,
                                       @RequestParam(value = "customer_id", required = false) Long customerProfileId) throws DomainValidationException {
        ModelAndView modelAndView;
        if (JwtSecurityContextHolder.hasRole(Role.CUSTOMER)) {
            modelAndView = new ModelAndView("booking-form");
        } else {
            modelAndView = new ModelAndView("bo/bo-booking-form");
        }
        List<PaymentMethod> paymentMethods = new ArrayList<>(Arrays.stream(PaymentMethod.values()).toList());
        paymentMethods.remove(paymentMethods.size() - 1);
        modelAndView.addObject("paymentMethods", paymentMethods);
        modelAndView.addObject("invoiceDeliveries", Arrays.stream(InvoiceDelivery.values()).toList());

        modelAndView.addObject("customerId", customerProfileId);
        modelAndView.addObject("activeEvent", activeEventService.getActiveEventById(id));
        return modelAndView;
    }



    @PostMapping("/active-events/{id}/booking")
    public String sendBookingForm(@ModelAttribute("createBooking") CreateBookingDTO createBookingDTO,
                                  @PathVariable("id") Long eventId) {
        try {
            bookingService.createBooking(createBookingDTO, eventId);
        } catch (DomainValidationException | SimpleValidationException e) {
            ValidationExceptionToBindingResultFactory.handle(e, createBookingDTO, "active-events/" + eventId + "/booking");
        }
        return "redirect:/bookings";
    }

    @GetMapping("/bookings")
    public ModelAndView getAllBooking(){
        ModelAndView modelAndView = new ModelAndView("all-bookings");
        modelAndView.addObject("bookings",bookingService.getAllBooking());
        modelAndView.addObject("qrCodeImage", QRCodeGenerator.generateQRCodeImage("localhost8080","Halllo",100,100));
        List<BookingListDTO> bookingListDTOS = bookingService.getAllBooking();
        modelAndView.addObject("bookings",bookingListDTOS);
        return modelAndView;
    }

    @GetMapping("/bookings/checkin")
    public String checkinBooking(@RequestParam("token")String token){
        bookingService.checkInBooking(token);
        return "redirect:/bookings";
        //TODO: redirect to Dashboard for active event
    }
}

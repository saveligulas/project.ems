package fhv.team11.project.ems.booking.controller;

import fhv.team11.project.ems.booking.service.BookingService;
import fhv.team11.project.ems.booking.service.CheckInException;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.qrcode.QRCodeGenerator;
import fhv.team11.project.ems.commons.validation.ValidationExceptionToBindingResultFactory;
import fhv.team11.project.ems.commons.validation.domain.handler.IHandleBindingResultException;
import fhv.team11.project.ems.commons.validation.error.SimpleValidationException;
import fhv.team11.project.ems.domain.booking.InvoiceDelivery;
import fhv.team11.project.ems.domain.booking.PaymentMethod;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.events.service.ActiveEventService;
import fhv.team11.project.ems.events.service.EventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.permission.role.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookingController implements IHandleBindingResultException {
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

    @GetMapping("/event/{templateId}/active/{id}/booking")
    public ModelAndView getBookingForm(@PathVariable("id") Long id,
                                       @PathVariable("templateId") Long templateId,
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

        modelAndView.addObject("templateId", templateId);
        modelAndView.addObject("activeEventId", id);
        return modelAndView;
    }



    @PostMapping("/event/{templateId}/active/{id}/booking/create")
    public String sendBookingForm(@PathVariable("id") Long eventId,
                                  @PathVariable("templateId") Long templateId,
                                  @Valid @ModelAttribute("createBooking") CreateBookingDTO createBookingDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addBindingResultToRedirect("createBooking", bindingResult, redirectAttributes);
            return "redirect:/event/" + templateId + "/active/" + eventId + "/booking";
        }

        try {
            bookingService.createBooking(createBookingDTO, eventId);
        } catch (DomainValidationException | SimpleValidationException e) {
            ValidationExceptionToBindingResultFactory.handle(e, createBookingDTO, "event/" + templateId + "/active/" + eventId + "/booking");
        }
        return "redirect:/bookings";
    }

    @GetMapping("/bookings")
    public ModelAndView getBookingsForCustomerProfile(@RequestParam(name = "financerId", required = false) Long financerId){
        ModelAndView modelAndView;
        if (JwtSecurityContextHolder.hasRole(Role.CUSTOMER)) {
            modelAndView = new ModelAndView("cu/cu-booking-list");
            if (JwtSecurityContextHolder.hasCustomerProfile()) {
                modelAndView.addObject("bookings", bookingService.getBookingsBySecurityContext());
            }
        } else {
            modelAndView = new ModelAndView("bo/bo-booking-list");
            if (financerId != null) {
                modelAndView.addObject("bookings", bookingService.getBookingsByCustomerProfileId(financerId));
                modelAndView.addObject("financerId", financerId);
            }
        }
        return modelAndView;
    }

    //TODO: implement global handler for domain validation if backend error occurs without user input
    @GetMapping("/bookings/checkin")
    public String checkinBooking(@RequestParam("token")String token) throws DomainValidationException {
        try {
            bookingService.checkInParticipant(token);
        } catch (CheckInException e) {
            return "fo/fo-booking-status-invalid";
        }

        return "fo/fo-booking-status-valid";
        //TODO: redirect to Dashboard for active event
    }
}

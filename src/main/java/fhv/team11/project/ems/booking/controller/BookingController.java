package fhv.team11.project.ems.booking.controller;

import fhv.team11.project.ems.booking.service.BookingService;
import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.commons.qrcode.QRCodeGenerator;
import fhv.team11.project.ems.events.service.ActiveEventService;
import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BookingController {
    private final BookingService bookingService;
    private final ActiveEventWizardService activeEventWizardService;
    private final ActiveEventService activeEventService;

    @Autowired
    public BookingController(BookingService bookingService, ActiveEventWizardService activeEventWizardService, ActiveEventService activeEventService, EventTemplateService eventTemplateService) {
        this.bookingService = bookingService;
        this.activeEventWizardService = activeEventWizardService;
        this.activeEventService = activeEventService;
    }


    @GetMapping("/active-events/{id}/booking")
    public ModelAndView getBookingForm(@PathVariable("id") Long id) {
        ActiveEventView activeEvent = activeEventService.getActiveEventById(id);
        ModelAndView modelAndView = new ModelAndView("booking-form");

        BookingDTO bookingDTO = new BookingDTO();
        ActiveEventListDTO bookedEvent = new ActiveEventListDTO();
        bookedEvent.setId(activeEvent.getActiveEventListDTO().getId());
        bookingDTO.setBookedEvent(bookedEvent);

        modelAndView.addObject("activeEvent", activeEvent);
        modelAndView.addObject("booking", bookingDTO);
        return modelAndView;
    }



    @PostMapping("/active-events/{id}/booking")
    public String sendBookingForm(@ModelAttribute("booking") BookingDTO bookingDTO,
                                  @ModelAttribute("EventPrice") BigDecimal eventPrice) {
        bookingDTO.setPrice(eventPrice);
        bookingService.createBooking(bookingDTO);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings")
    public ModelAndView getAllBooking(){
        ModelAndView modelAndView = new ModelAndView("all-bookings");
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

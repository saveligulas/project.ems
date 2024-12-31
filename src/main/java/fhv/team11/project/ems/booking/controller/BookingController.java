package fhv.team11.project.ems.booking.controller;

import fhv.team11.project.ems.booking.service.BookingService;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.qrcode.QRCodeGenerator;
import fhv.team11.project.ems.events.service.ActiveEventService;
import fhv.team11.project.ems.events.service.EventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookingController {
    private final BookingService bookingService;
    private final EventWizardService eventWizardService;
    private final ActiveEventService activeEventService;

    @Autowired
    public BookingController(BookingService bookingService, EventWizardService eventWizardService, ActiveEventService activeEventService, EventTemplateService eventTemplateService) {
        this.bookingService = bookingService;
        this.eventWizardService = eventWizardService;
        this.activeEventService = activeEventService;
    }


    @GetMapping("/active-events/{id}/booking")
    public ModelAndView getBookingForm(@PathVariable("id") Long id) {
        ActiveEventView activeEvent = activeEventService.getActiveEventById(id);
        ModelAndView modelAndView = new ModelAndView("booking-form");

        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        ActiveEventListDTO activeEventListDTO = new ActiveEventListDTO();
        activeEventListDTO.setId(activeEvent.getActiveEventListDTO().getId());

        modelAndView.addObject("activeEvent", activeEvent);
        modelAndView.addObject("activeEventList", activeEventListDTO);
        modelAndView.addObject("createBooking", createBookingDTO);
        return modelAndView;
    }



    @PostMapping("/active-events/{id}/booking")
    public String sendBookingForm(@ModelAttribute("createBooking") CreateBookingDTO createBookingDTO,
                                  @PathVariable("id") Long eventId) {
        bookingService.createBooking(createBookingDTO, eventId);
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

package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.repo.BookingRepository;
import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.commons.address.AddressRepository;
import fhv.team11.project.ems.events.repo.ActiveEventRepository;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import fhv.team11.project.ems.user.repo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserJDBCRepository userJDBCRepository;
    private final ActiveEventRepository activeEventRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserJDBCRepository userJDBCRepository, ActiveEventRepository activeEventRepository, AddressRepository addressRepository) {
        this.bookingRepository = bookingRepository;
        this.userJDBCRepository = userJDBCRepository;
        this.activeEventRepository = activeEventRepository;
        this.addressRepository = addressRepository;
    }



    public void createBooking(BookingDTO bookingDTO) {

        /*Booking booking = new Booking();
        booking.setBookedEvent();
        if (bookingDTO.getParticipant().equals(bookingDTO.getReservationist())){
            User user = new User();
            user = userJDBCRepository.findById(bookingDTO.getParticipant());
            booking.setParticipant(user);
            booking.setReservationist(user);
        }else {
            User participant = new User();
            User reservationist = new User();
            participant = userJDBCRepository.findById(bookingDTO.getParticipant());
            reservationist = userJDBCRepository.findById(bookingDTO.getReservationist());
            booking.setParticipant(participant);
            booking.setReservationist(reservationist);
        }
        Address address = new Address();
        booking.setParticipantAddress(addressRepository.findById(bookingDTO.getParticipantAddress()));

        bookingRepository.persist(booking);*/
    }
}

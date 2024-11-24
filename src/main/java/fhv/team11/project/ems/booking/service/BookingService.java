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

        Booking booking = BookingDTOMapper.INSTANCE.getEntity(bookingDTO);
        booking.setBookedEvent(activeEventRepository.findById(booking.getBookedEvent().getId()).orElse(null));

        if(addressRepository.findById(booking.getParticipantAddress().getId()).orElse(null)!=null){
            booking.setParticipantAddress(addressRepository.findById(booking.getParticipantAddress().getId()).orElse(null));
        }


        bookingRepository.persist(booking);
    }
}

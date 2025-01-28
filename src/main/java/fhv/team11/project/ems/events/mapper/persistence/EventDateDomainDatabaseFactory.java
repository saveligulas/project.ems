package fhv.team11.project.ems.events.mapper.persistence;

import fhv.team11.project.ems.booking.repo.BookingIdentifier;
import fhv.team11.project.ems.booking.repo.BookingIdentifierRepository;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.events.repo.EventDateEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@NullMarked
public class EventDateDomainDatabaseFactory implements ISimpleDomainDatabaseMapper<EventDate, EventDateEntity> {
    private final BookingIdentifierRepository bookingIdentifierRepository;

    @Autowired
    public EventDateDomainDatabaseFactory(BookingIdentifierRepository bookingIdentifierRepository) {
        this.bookingIdentifierRepository = bookingIdentifierRepository;
    }

    @Override
    public EventDateEntity toEntity(EventDate domain) {
        return new EventDateEntity(
                domain.getId(),
                domain.getDate(),
                domain.getName(),
                EventScheduleDomainMapper.INSTANCE.toEntity(domain.getSchedule()),
                domain.getCheckedInBookingIdentifiers().stream()
                        .map(uuid -> bookingIdentifierRepository != null
                                ? bookingIdentifierRepository.findById(uuid)
                                .orElse(new BookingIdentifier(uuid, null, null))
                                : new BookingIdentifier(uuid, null, null))
                        .toList());
    }

    @Override
    public EventDate toDomain(EventDateEntity entity) throws DomainValidationException {
        return new EventDate(
                entity.getId(),
                entity.getDate(),
                entity.getName(),
                EventScheduleDomainMapper.INSTANCE.toDomain(entity.getSchedule()),
                entity.getCheckedInBookingIdentifiers().stream()
                        .map(BookingIdentifier::getToken)
                        .collect(Collectors.toList()));
    }
}
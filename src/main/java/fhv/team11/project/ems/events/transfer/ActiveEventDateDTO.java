package fhv.team11.project.ems.events.transfer;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
public class ActiveEventDateDTO implements Comparable<ActiveEventDateDTO> {
    private LocalDate date;
    private String name;

    @Override
    public int compareTo(ActiveEventDateDTO o) {
        return this.date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveEventDateDTO that = (ActiveEventDateDTO) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }
}

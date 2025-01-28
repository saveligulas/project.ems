package fhv.team11.project.ems.events.transfer;

import lombok.Data;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;

@Data
public class ActiveEventListView {
    private Long id;
    private LocalDate start;
    @Nullable
    private LocalDate end;
    private int bookedPlaces;
}

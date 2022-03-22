package rw.bkg.taxi24.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "TRIP")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            initialValue = 1000,
            name = "trip_id_seq",
            sequenceName = "trip_id_seq",
            allocationSize = 1)
    private Long id;
    private TripStatus status;
    private String startStationId;
    private String endStationId;
    private String startTime;
    private String endTime;
    private String duration;
    private String distance;
    @ManyToOne
    @JoinColumn(name = "driver_id",
    nullable = false)
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "rider_id",
    nullable = false)
    private Rider rider;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Trip trip = (Trip) o;
        return id != null && Objects.equals(id, trip.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

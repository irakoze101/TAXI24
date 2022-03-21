package models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "DRIVER")
public class Driver {
    @Id
    private String licenseId;
    private String locationId;
    private String name;
    @OneToMany(mappedBy = "driver")
    @ToString.Exclude
    private List<Trip> trips;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Driver driver = (Driver) o;
        return licenseId != null && Objects.equals(licenseId, driver.licenseId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

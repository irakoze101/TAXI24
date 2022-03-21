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
@Entity(name = "RIDER")
public class Rider {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String birthDate;
    private double latitude;
    private double longitude;
    @OneToMany(mappedBy = "rider")
    @ToString.Exclude
    private List<Trip> trips;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rider rider = (Rider) o;
        return id != null && Objects.equals(id, rider.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

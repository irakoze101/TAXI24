package rw.bkg.taxi24.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

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
    @JsonIgnore
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

    @PrePersist
    public void prePersist() {
        if (id == null) {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(messageDigest).update(UUID.randomUUID().toString().getBytes(
                    StandardCharsets.UTF_8));
            id = String.format(Locale.ENGLISH,
                    "%032x",
                    new BigInteger(1, messageDigest
                            .digest()))
                    .toUpperCase(Locale.ROOT);
        }
    }
}

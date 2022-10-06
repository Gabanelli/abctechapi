package br.com.fiap.abctechapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double latitude;
    private Double longitude;
    private Date date;

    public OrderLocation(Double latitude, Double longitude, Date date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }
}

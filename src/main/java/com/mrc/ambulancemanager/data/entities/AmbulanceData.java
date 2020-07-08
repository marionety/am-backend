package com.mrc.ambulancemanager.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mrc.ambulancemanager.app.api.entities.AmbulanceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ambulances")
@Table(name = "ambulances")
public class AmbulanceData {
    @Id
    private Long id;

    @Column(nullable = false)
    private Integer seats;

    private static AmbulanceData from(AmbulanceRequest ambulance) {
        return new AmbulanceData(ambulance.getAmbulanceRequestID(), ambulance.getSeats());
    }

    public static List<AmbulanceData> from(List<AmbulanceRequest> ambulances) {
        List<AmbulanceData> result = new ArrayList<AmbulanceData>();
        for (AmbulanceRequest ambulance : ambulances) {
            result.add(from(ambulance));
        }
        return result;
    }
}
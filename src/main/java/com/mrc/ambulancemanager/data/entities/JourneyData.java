package com.mrc.ambulancemanager.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mrc.ambulancemanager.domain.entities.Journey;
import com.mrc.ambulancemanager.domain.entities.JourneyStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "journeys")
@Table(name = "journeys")
public class JourneyData {

    @Id
    private Long id;

    @Column(nullable = false)
    private Integer people;

    @Column(nullable = false)
    private JourneyStatus status;

    @OneToOne
    @JoinTable(name = "trackings", joinColumns = {
            @JoinColumn(name = "journey_id", referencedColumnName = "id", unique = true) }, inverseJoinColumns = {
                    @JoinColumn(name = "ambulance_id", referencedColumnName = "id") })
    private AmbulanceData ambulance;

    public static JourneyData from(Journey journey) {
        return new JourneyData(journey.getId(), journey.getPeople(), journey.getStatus(), null);
    }
}
package com.mrc.ambulancemanager.data.repositories.journey;

import java.util.List;

import com.mrc.ambulancemanager.data.entities.JourneyData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<JourneyData, Long> {

    @Query(value = "select sum(people) from trackings left join journeys on journey_id = id where ambulance_id = :ambulanceId", nativeQuery = true)
    Integer findPeopleJourneysByAmbulanceDataId(@Param("ambulanceId") Long ambulanceId);

    @Query(value = "select j from journeys j where j.status = 0 and j.people <= :seats")
    List<JourneyData> findWaitingGroupsThatFitInAmbulanceSeats(@Param("seats") Integer ambulanceEmptySeats);
}
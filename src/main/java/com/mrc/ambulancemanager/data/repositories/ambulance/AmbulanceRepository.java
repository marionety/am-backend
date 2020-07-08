package com.mrc.ambulancemanager.data.repositories.ambulance;

import java.util.List;

import com.mrc.ambulancemanager.data.entities.AmbulanceData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbulanceRepository extends JpaRepository<AmbulanceData, Long> {

    List<AmbulanceData> findAllByOrderByIdAsc();

}
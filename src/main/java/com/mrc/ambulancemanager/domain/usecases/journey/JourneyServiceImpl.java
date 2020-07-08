package com.mrc.ambulancemanager.domain.usecases.journey;

import java.util.List;
import java.util.Optional;

import com.mrc.ambulancemanager.app.api.entities.JourneyRequest;
import com.mrc.ambulancemanager.app.api.entities.TransferRequest;
import com.mrc.ambulancemanager.data.entities.AmbulanceData;
import com.mrc.ambulancemanager.data.entities.JourneyData;
import com.mrc.ambulancemanager.data.repositories.ambulance.AmbulanceRepository;
import com.mrc.ambulancemanager.data.repositories.journey.JourneyRepository;
import com.mrc.ambulancemanager.domain.entities.Journey;
import com.mrc.ambulancemanager.domain.entities.JourneyStatus;
import com.mrc.ambulancemanager.domain.errors.exceptions.AlreadyCompletedJourneyException;
import com.mrc.ambulancemanager.domain.errors.exceptions.ExistingJourneyException;
import com.mrc.ambulancemanager.domain.errors.exceptions.NotExistingJourneyException;
import com.mrc.ambulancemanager.domain.errors.exceptions.UnexpectedJourneyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    AmbulanceRepository ambulanceRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Journey createJourney(JourneyRequest request) {
        Journey journey = Journey.from(request);
        Journey createdJourney = null;
        try {
            if (journeyRepository.findById(journey.getId()).isEmpty()) {
                JourneyData savedJourneyData = findEmptyAmbulanceSeatsForJourney(JourneyData.from(journey));
                createdJourney = Journey.from(savedJourneyData);
            } else {
                throw new ExistingJourneyException(String.valueOf(journey.getId()));
            }
        } catch (IllegalArgumentException exception) {
            throw new UnexpectedJourneyException(String.valueOf(journey.getId()));
        }
        return createdJourney;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Journey finishJourney(TransferRequest request) {
        try {
            Optional<JourneyData> optionalJourneyData = journeyRepository.findById(request.getId());
            if (optionalJourneyData.isEmpty()) {
                throw new NotExistingJourneyException(String.valueOf(request.getId()));
            } else if (optionalJourneyData.get().getStatus() == JourneyStatus.COMPLETED) {
                throw new AlreadyCompletedJourneyException(String.valueOf(request.getId()));
            } else {
                AmbulanceData emptySeatsAmbulance = optionalJourneyData.get().getAmbulance();
                Journey journey = Journey.from(optionalJourneyData.get());
                journey.setStatus(JourneyStatus.COMPLETED);
                JourneyData savedJourneyData = journeyRepository.save(JourneyData.from(journey));
                if (emptySeatsAmbulance != null) {
                    updateWaitingJourneys(emptySeatsAmbulance);
                }
                return Journey.from(savedJourneyData);
            }
        } catch (IllegalArgumentException exception) {
            throw new UnexpectedJourneyException(String.valueOf(request.getId()));
        }
    }

    /**
     * Looks for the first ambulance where the group can fit in (if any)
     * 
     * @param journeyData group info.
     * @return group saved in repository.
     */
    private JourneyData findEmptyAmbulanceSeatsForJourney(JourneyData journeyData) {
        List<AmbulanceData> ambulances = ambulanceRepository.findAllByOrderByIdAsc();
        // Find first ambulance with empty seats where group fits
        Optional<AmbulanceData> emptyAmbulance = ambulances.stream().filter(
                ambulance -> ambulance.getSeats() - getAmbulanceUsedSeats(ambulance.getId()) >= journeyData.getPeople())
                .findFirst();
        if (emptyAmbulance.isPresent()) {
            journeyData.setAmbulance(emptyAmbulance.get());
            journeyData.setStatus(JourneyStatus.IN_PROGRESS);
        }
        return journeyRepository.save(journeyData);
    }

    /**
     * Gets how many seats are used in the ambulance
     * 
     * @param ambulanceId
     * @return used seats
     */
    private int getAmbulanceUsedSeats(Long ambulanceId) {
        Integer usedSeats = journeyRepository.findPeopleJourneysByAmbulanceDataId(ambulanceId);
        if (usedSeats == null) {
            usedSeats = 0;
        }
        return usedSeats;
    }

    /**
     * Looks for waiting groups to be picked up when an ambulance has new empty
     * seats.
     * 
     * @param ambulance with new empty seats
     */
    private void updateWaitingJourneys(AmbulanceData ambulance) {
        int ambulanceEmptySeats = ambulance.getSeats() - getAmbulanceUsedSeats(ambulance.getId());
        List<JourneyData> journeys = journeyRepository.findWaitingGroupsThatFitInAmbulanceSeats(ambulanceEmptySeats);
        for (JourneyData journey : journeys) {
            if (journey.getPeople() <= ambulanceEmptySeats) {
                journey.setAmbulance(ambulance);
                journey.setStatus(JourneyStatus.IN_PROGRESS);
                journeyRepository.save(journey);
                ambulanceEmptySeats -= journey.getPeople();
            }
        }
    }
}
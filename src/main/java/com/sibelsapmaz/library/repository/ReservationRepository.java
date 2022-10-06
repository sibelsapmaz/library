package com.sibelsapmaz.library.repository;

import com.sibelsapmaz.library.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> getReservationsByMemberId(Long memberId);
    Optional<Reservation> getReservationsByBook_IdAndReturnedFalse(Long bookId);
}

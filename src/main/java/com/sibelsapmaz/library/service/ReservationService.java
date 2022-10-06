package com.sibelsapmaz.library.service;

import com.sibelsapmaz.library.dto.ReservationDto;
import com.sibelsapmaz.library.entity.Book;
import com.sibelsapmaz.library.entity.Reservation;
import com.sibelsapmaz.library.repository.ReservationRepository;
import com.sibelsapmaz.library.request.CreateReservationRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookService bookService;

    public ReservationService(ReservationRepository reservationRepository, BookService bookService) {
        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
    }

    public List<ReservationDto> getMemberReservations(Long memberId) {
        return reservationRepository.getReservationsByMemberId(memberId)
                .stream()
                .map(ReservationService::mapToReservationDto)
                .collect(Collectors.toList());
    }

    private static ReservationDto mapToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setAuthor(reservation.getBook().getAuthor());
        reservationDto.setBookName(reservation.getBook().getAuthor());
        reservationDto.setEndDate(reservation.getEndDate());
        return reservationDto;
    }

    public void createReservation(CreateReservationRequest request) {
        Optional<Reservation> reservation = reservationRepository.getReservationsByBook_IdAndReturnedFalse(request.getBookId());
        if (reservation.isPresent()) {
            throw new RuntimeException("This book is already reserved");
        }
        reservationRepository.save(mapToReservation(request));

    }

    private Reservation mapToReservation(CreateReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setEndDate(request.getEndDate());
        reservation.setReturned(false);
        reservation.setStartDate(new Date());
        Optional<Book> book = bookService.getBook(request.getBookId());
        reservation.setBook(book.get());
        reservation.setMemberId(request.getMemberId());
        return reservation;
    }
}

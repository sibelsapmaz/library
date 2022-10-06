package com.sibelsapmaz.library.controller;

import com.sibelsapmaz.library.dto.ReservationDto;
import com.sibelsapmaz.library.request.CreateReservationRequest;
import com.sibelsapmaz.library.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/library/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/user/{memberId}")
    public ResponseEntity<List<ReservationDto>> getMemberReservations(@PathVariable Long memberId) {
        List<ReservationDto> memberReservations = reservationService.getMemberReservations(memberId);
        return ResponseEntity.ok(memberReservations);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createReservation(@RequestBody CreateReservationRequest request) {
        reservationService.createReservation(request);
    }
}

package rw.bkg.taxi24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.bkg.taxi24.services.impl.RiderServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/taxi24/riders")
public class RidersController {
    final RiderServiceImpl riderService;

    public RidersController(RiderServiceImpl riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getRiders() {
        return ResponseEntity.ok(riderService.getAllRiders());
    }
}

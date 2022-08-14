package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.controller.DTO.CreateDTO;
import one.digitalinnovation.parking.controller.DTO.ParkingDTO;
import one.digitalinnovation.parking.controller.mapper.Mapper;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Park Controller")
public class ParkController {

    private ParkingService parkingService;
    private Mapper mapper;

    public ParkController(ParkingService parkingService, Mapper mapper) {
        this.parkingService = parkingService;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation("Find all parking vehicles")
    public ResponseEntity<List<ParkingDTO>> findVehicle(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = mapper.toParkingList(parkingList);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Create parking vehicles")
    public  ResponseEntity<ParkingDTO> create(@RequestBody CreateDTO parkingDTO){
        Parking parkingCreate = mapper.toParkingCreate(parkingDTO);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = mapper.parkingDTO(parking);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find one specific parking vehicle")
    public  ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking = parkingService.findById(id).get();
        ParkingDTO result = mapper.parkingDTO(parking);
        return ResponseEntity.ok(result);
    }
}

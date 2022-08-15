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
    @ApiOperation("Find one specific parking vehicle id")
    public  ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking = parkingService.findById(id);
        ParkingDTO result = mapper.parkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclude one specific parking vehicle by id")
    public ResponseEntity delete(@PathVariable String id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Udpate one specific parking vehicle by id")
    public  ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody CreateDTO dto){
        Parking parkingCreate = mapper.toParkingCreate(dto);
        Parking parking = parkingService.update(id,parkingCreate);
        ParkingDTO result = mapper.parkingDTO(parking);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO> exitVehicle(@PathVariable String id){
        Parking parking = parkingService.exit(id);
        return ResponseEntity.ok(mapper.parkingDTO(parking));
    }
}

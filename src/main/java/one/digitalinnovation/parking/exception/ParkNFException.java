package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkNFException extends RuntimeException {


    public ParkNFException(String id) {
        super("Parking not found with id: " + id);
    }
}

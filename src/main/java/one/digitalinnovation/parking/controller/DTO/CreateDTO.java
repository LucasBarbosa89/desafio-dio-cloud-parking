package one.digitalinnovation.parking.controller.DTO;

import lombok.Data;

@Data
public class CreateDTO {
    private String license;
    private String state;
    private String model;
    private String color;
}

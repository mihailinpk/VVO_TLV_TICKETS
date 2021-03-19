package main.entitites;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Ticket {
    String origin;
    String origin_name;
    String destination;
    String destination_name;
    @JsonFormat(pattern = "dd.MM.yy")
    LocalDate departure_date;
    @JsonFormat(pattern = "H:mm")
    LocalTime departure_time;
    @JsonFormat(pattern = "dd.MM.yy")
    LocalDate arrival_date;
    @JsonFormat(pattern = "H:mm")
    LocalTime arrival_time;
    String carrier;
    Integer stops;
    Integer price;
}
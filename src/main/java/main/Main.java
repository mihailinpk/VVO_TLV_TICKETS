package main;

import main.controllers.TicketsController;
import main.entitites.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final static String JSON_FILE = "src/main/resources/tickets.json";
    private final static String JSON_TICKETS_LIST_NAME = "tickets";

    private final static String ORIGIN = "VVO";
    private final static String DESTINATION = "TLV";
    private final static int PERCENTILE_VALUE = 90;

    private TicketsController controller;

    @Autowired
    public void setController(TicketsController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {

        try {
            List<Ticket> ticketsList = controller.parseJsonFileToTicketsList(JSON_FILE, JSON_TICKETS_LIST_NAME);
            controller.calculateAndPrintAverageFlightTime(ticketsList, ORIGIN, DESTINATION);
            controller.calculateAndPrintPercentile(ticketsList, PERCENTILE_VALUE);
        } catch (Exception ex)  {
            ex.printStackTrace();
        }

    }

}
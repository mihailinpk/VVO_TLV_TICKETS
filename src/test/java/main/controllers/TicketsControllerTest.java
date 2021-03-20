package main.controllers;

import junit.framework.TestCase;
import main.entitites.Ticket;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketsControllerTest extends TestCase {

    private final static String JSON_TEST_FILE = "src/test/resources/tickets_test.json";
    private final static String JSON_TICKETS_TEST_LIST_NAME = "tickets_test";

    private List<Ticket> ticketListForTest = new ArrayList<>();

    private TicketsController ticketsController = new TicketsController();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Ticket ticket1 = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив",
                LocalDate.of(2018, 5, 12), LocalTime.of(17, 20),
                LocalDate.of(2018, 5, 12), LocalTime.of(23, 50), "S7",
                1, 13100
        );
        Ticket ticket2 = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив",
                LocalDate.of(2018, 5, 12), LocalTime.of(17, 0),
                LocalDate.of(2018, 5, 12), LocalTime.of(23, 30), "TK",
                2, 11000
        );
        Ticket ticket3 = new Ticket("VVO","Владивосток","TLV","Тель-Авив",
                LocalDate.of(2018, 5, 12), LocalTime.of(9, 40),
                LocalDate.of(2018, 5, 12), LocalTime.of(19, 25),"SU",
                3,12450
        );
        Ticket ticket4 = new Ticket("VVO","Владивосток","TLV","Тель-Авив",
                LocalDate.of(2018, 5, 12), LocalTime.of(6, 10),
                LocalDate.of(2018, 5, 12), LocalTime.of(15, 25),"TK",
                0,14250
        );
        ticketListForTest.addAll(Arrays.asList(ticket1, ticket2, ticket3, ticket4));
    }

    @Test
    public void testParseJsonFileToTicketsList() throws IOException {
        List<Ticket> actual = ticketListForTest;
        List<Ticket> expected = ticketsController.parseJsonFileToTicketsList(JSON_TEST_FILE, JSON_TICKETS_TEST_LIST_NAME);
        assertEquals(expected, actual);
    }

}
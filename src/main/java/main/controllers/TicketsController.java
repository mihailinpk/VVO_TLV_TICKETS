package main.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import main.entitites.Ticket;
import main.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class TicketsController {

    private TicketsService service;

    @Autowired
    public void setService(TicketsService service) {
        this.service = service;
    }

    public List<Ticket> parseJsonFileToTicketsList(String jsonFile, String jsonValueName) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        TypeReference<Map<String, List<Ticket>>> typeReference = new TypeReference<>() {};
        Map<String, List<Ticket>> ticketsMap = mapper.readValue(Paths.get(jsonFile).toFile(), typeReference);
        return ticketsMap.get(jsonValueName);
    }

    public void calculateAndPrintAverageFlightTime(List<Ticket> ticketList, String origin, String destination)    {
        if (ticketList.size() == 0)    {
            System.out.println("Считать нечего");
        } else {
            long totalNumberOfSeconds = 0;
            int sumTickets = 0;
            for(int i=0; i<ticketList.size(); i++) {
                Ticket currentTicket = ticketList.get(i);
                if (service.checkOriginAndDestination(currentTicket, origin, destination))  {
                    sumTickets = sumTickets + 1;
                    totalNumberOfSeconds = totalNumberOfSeconds +
                            service.getDurationSecBetweenDepartureAndArrivalDateTime(ticketList.get(i));
                }
            }
            Duration averageDuration = Duration.ofSeconds(totalNumberOfSeconds / sumTickets);
            System.out.println("Среднее время полета между городами:");
            service.printDuration(averageDuration);
        }
    }

    public void calculateAndPrintPercentile(List<Ticket> ticketList, int percentileValue)   {
        List<Long> durationSecList = new ArrayList<>();
        for(Ticket ticket : ticketList)    {
            durationSecList.add(service.getDurationSecBetweenDepartureAndArrivalDateTime(ticket));
        }
        Collections.sort(durationSecList);
        int indexOfElement = service.getIndexOfListForPercetnile(durationSecList, percentileValue);
        Duration percentileDuration = Duration.ofSeconds(durationSecList.get(indexOfElement));
        System.out.println("\n" + percentileValue + "-й процентиль:");
        service.printDuration(percentileDuration);
    }

}
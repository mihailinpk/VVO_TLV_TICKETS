package main.services;

import main.entitites.Ticket;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketsService {

    private final int TOTAL_PERCENTS = 100;

    public long getDurationSecBetweenDepartureAndArrivalDateTime(Ticket ticket)    {
        LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDeparture_date(), ticket.getDeparture_time());
        LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrival_date(), ticket.getArrival_time());
        return Duration.between(departureDateTime, arrivalDateTime).toSeconds();
    }

    public int getIndexOfListForPercetnile(List<Long> duratinSecList, int percentileValue) {
        return percentileValue*duratinSecList.size()/TOTAL_PERCENTS;
    }

    public boolean checkOriginAndDestination(Ticket ticket, String origin, String destination)  {
        return  ticket.getOrigin().equals(origin) && ticket.getDestination().equals(destination);
    }

    public void printDuration(Duration duration)    {
        System.out.print(duration.toDaysPart() + " дней, " + duration.toHoursPart() + " часов, "
                + duration.toMinutesPart() + " минут, " + duration.toSecondsPart() + " секунд");
    }

}

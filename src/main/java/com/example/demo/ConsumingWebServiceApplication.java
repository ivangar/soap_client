package com.example.demo;

import com.example.demo.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@SpringBootApplication
public class ConsumingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(LogsClient quoteClient) {
        return args -> {
            GetAllLogsResponse response = quoteClient.getLogs();
            List<LogEntry> logs = response.getLogEntry();
            printLogs(logs);

            String change = "delete";
            GetAllLogsByChangeResponse responseByChange = quoteClient.getLogsByChange(change);
            logs = responseByChange.getLogEntry();
            printLogs(logs);

            String from = "2021-03-02";
            String to = "2021-03-06";
            GetAllLogsByDatesResponse responseByDates = quoteClient.getLogsByDateRange(from, to);
            logs = responseByDates.getLogEntry();
            printLogs(logs);

            GetAllLogsByByDateAndChangeResponse responseByDatesAndChange = quoteClient.getLogsByDateRangeAndChange(from, to, change);
            logs = responseByDatesAndChange.getLogEntry();
            printLogs(logs);

        };
    }

    public static void printLogs(List<LogEntry> logs){
        System.out.println(" Id   ISRC   Change   Time stamp  \n");
        logs.forEach(e -> System.out.println("{" + e.getLogId() + ", "+ e.getISRC() + ", " + e.getTypeOfChange() + ", " + e.getTimeStamp() + "}"));
    }

}

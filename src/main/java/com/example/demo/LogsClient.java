package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import com.example.demo.wsdl.GetAllLogsRequest;
import com.example.demo.wsdl.GetAllLogsResponse;
import com.example.demo.wsdl.GetAllLogsByChangeRequest;
import com.example.demo.wsdl.GetAllLogsByChangeResponse;
import com.example.demo.wsdl.GetAllLogsByDatesRequest;
import com.example.demo.wsdl.GetAllLogsByDatesResponse;
import com.example.demo.wsdl.GetAllLogsByByDateAndChangeRequest;
import com.example.demo.wsdl.GetAllLogsByByDateAndChangeResponse;

public class LogsClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(LogsClient.class);

    public GetAllLogsResponse getLogs() {

        GetAllLogsRequest request = new GetAllLogsRequest();

        log.info("\n\nRequesting location for all logs\n\n");

        GetAllLogsResponse response = (GetAllLogsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/logs.wsdl", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/getAllLogsRequest"));

        return response;
    }

    public GetAllLogsByChangeResponse getLogsByChange(String change_type) {

        GetAllLogsByChangeRequest request = new GetAllLogsByChangeRequest();
        request.setChangeType(change_type);
        log.info("\n\nRequesting location for all logs filtered by change type of " + change_type + "\n\n");

        GetAllLogsByChangeResponse response = (GetAllLogsByChangeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/logs.wsdl", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/getAllLogsByChangeRequest"));

        return response;
    }

    public GetAllLogsByDatesResponse getLogsByDateRange(String from, String to) {

        GetAllLogsByDatesRequest request = new GetAllLogsByDatesRequest();
        request.setFrom(from);
        request.setTo(to);
        log.info("\n\nGetting all Log Entries from " + from + " to " + to + "\n\n");

        GetAllLogsByDatesResponse response = (GetAllLogsByDatesResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/logs.wsdl", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/getAllLogsByDatesResponse"));

        return response;
    }

    public GetAllLogsByByDateAndChangeResponse getLogsByDateRangeAndChange(String from, String to, String change_type) {

        GetAllLogsByByDateAndChangeRequest request = new GetAllLogsByByDateAndChangeRequest();
        request.setFrom(from);
        request.setTo(to);
        request.setChangeType(change_type);
        log.info("\n\nGetting all Log Entries from " + from + " to " + to + " and Change type of " + change_type + "\n\n");

        GetAllLogsByByDateAndChangeResponse response = (GetAllLogsByByDateAndChangeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/logs.wsdl", request,
                        new SoapActionCallback(
                                "http://localhost:8080/ws/getAllLogsByDatesResponse"));

        return response;
    }
}

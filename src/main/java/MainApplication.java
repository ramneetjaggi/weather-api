import service.AirportCodeService;

import java.io.IOException;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the airport identity code");
        String airportIdentity = scanner.nextLine();
        System.out.println("Please enter the metrics");
        System.out.println("Enter 1) Celsius 2) Fahrenheit ");
        String metric = scanner.next();
        String evaluateMetrics= metric.equals("1")?"metric":"imperial";
        AirportCodeService airportCodeService=new AirportCodeService();
        airportCodeService.readAirportCodes(evaluateMetrics,airportIdentity);
    }

}

package service;


import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NoArgsConstructor;
import model.AirportCode;
import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class AirportCodeService {
   private static String fileName = "src/main/resources/airport-codes_csv.csv";
    WeatherServiceAPI service = new WeatherServiceAPI();

    public  void readAirportCodes(String metrics, String airportIdentity) throws IOException {

        List<? extends Object> dataList = new CsvToBeanBuilder<>(new FileReader(fileName))
                .withType(AirportCode.class)
                .build()
                .parse();

        List<AirportCode> airportCode = (List<AirportCode>) dataList;
        String coordinates = airportCode.stream().filter(code -> code.getIdent().equals(airportIdentity) ).map(c -> c.getCoordinates()).collect(Collectors.joining(","));
        List<String> splitLatAndLong = Arrays.asList(coordinates.split(","));
        if (CollectionUtils.size(splitLatAndLong)==1) {
            System.out.println("Please enter a correct airport code");
        }
        else {
            System.out.println("The coordinates for the airport code " + airportIdentity + " are " + coordinates);

            service.getMaxTemp(splitLatAndLong,3,metrics);
        }

    }

}

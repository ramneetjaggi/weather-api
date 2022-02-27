package model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AirportCode {
    @CsvBindByName(column = "ident")
    private String ident	;
    @CsvBindByName(column = "type")
    private String type	;
    @CsvBindByName(column = "name")
    private String name	;
    @CsvBindByName(column = "elevation_ft")
    private String elevation_ft	;
    @CsvBindByName(column = "continent")
    private String continent;
    @CsvBindByName(column = "iso_country")
    private String iso_country	;
    @CsvBindByName(column = "iso_region")
    private String iso_region	;
    @CsvBindByName(column = "municipality")
    private String municipality;
    @CsvBindByName(column = "gps_code")
    private String gps_code	;
    @CsvBindByName(column = "iata_code")
    private String iata_code	;
    @CsvBindByName(column = "local_code")
    private String local_code;
    @CsvBindByName(column = "coordinates")
    private String coordinates;
}

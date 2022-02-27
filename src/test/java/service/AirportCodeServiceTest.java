package service;

import model.AirportCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AirportCodeServiceTest {

    @InjectMocks
    AirportCodeService airportCodeService;

@Mock
    WeatherServiceAPI weatherServiceAPI ;
//Mockito.spy(new WeatherServiceAPI());

   @Before
    public void setUp(){
       List<AirportCode> airportCodeList= new ArrayList<>();
       AirportCode airportCode= new AirportCode();
       airportCode.setIdent("ABC");
       airportCode.setCoordinates("123,456");
       AirportCode airportCode1= new AirportCode();
       airportCode1.setIdent("XYZ");
       airportCode1.setCoordinates("987,321");
       airportCodeList.add(airportCode1);

   }

   @Test
    public void readAirportCodesTest() throws IOException {

       //Act
       airportCodeService.readAirportCodes("metric","00AA");

      // Assert
     verify(weatherServiceAPI,times(1)).getMaxTemp
             (Arrays.asList("-101.473911"," 38.704022"),3,"metric");

    }
}
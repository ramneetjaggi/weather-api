package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature implements Serializable {

    private  double min;
    private  double max;
    private  double night;
    private  double eve;
    private  double morn;

}

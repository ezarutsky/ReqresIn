package reqresApi.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleResource {

    public Integer id;
    public String name;
    public Integer year;
    public String color;
    public String pantoneValue;

    public SingleResource() {
    }

    public SingleResource(Integer id, String name, Integer year, String color, String pantoneValue) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantoneValue = pantoneValue;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("year")
    public int getYear() {
        return year;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("pantone_value")
    public String getPantoneValue() {
        return pantoneValue;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPantoneValue(String pantoneValue) {
        this.pantoneValue = pantoneValue;
    }
}

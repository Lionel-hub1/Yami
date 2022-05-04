package lio.foody.yami;

public class Distances {
    int boundLength;
    String unitMeasure;

    public Distances(int boundLength, String unitMeasure) {
        this.boundLength = boundLength;
        this.unitMeasure = unitMeasure;
    }

    public int getBoundLength() {
        return boundLength;
    }

    public void setBoundLength(int boundLength) {
        this.boundLength = boundLength;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }
}

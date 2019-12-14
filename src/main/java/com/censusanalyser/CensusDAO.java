package com.censusanalyser;

public class CensusDAO {
    public String state;
    public String stateCode;
    public int population;
    public double populationDensity;
    public double totalArea;


    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(USCensusCode usCensusCSV) {
        state = usCensusCSV.state;
        stateCode = usCensusCSV.stateId;
        totalArea = usCensusCSV.totalArea;
        populationDensity = usCensusCSV.populationDensity;
        population = usCensusCSV.population;
    }

    public CensusDAO(){}

    public Object getCensesCSV(CensusAnalyser.Country country) {
        if(CensusAnalyser.Country.India.equals(country))
            return new IndiaCensusCSV(state, population, (int)populationDensity, (int)totalArea);
        return new USCensusCode(state, stateCode, population, totalArea, populationDensity);
    }

    @Override
    public String toString() {
        return "CensusDAO{" +
                "state='" + state + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", population=" + population +
                ", populationDensity=" + populationDensity +
                ", totalArea=" + totalArea +
                '}';
    }
}

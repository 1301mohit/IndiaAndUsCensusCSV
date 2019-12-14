package com.censusanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ParameterForSort {

    public enum Parameter {
        STATE, AREA, DENSITY, POPULATION
    }

    static Map<Parameter, Comparator> censusMap = new HashMap<>();

    public static Comparator<CensusDAO> getCensusMap(Parameter parameter) {
        Comparator<CensusDAO> comparatorForState = Comparator.comparing(census -> census.state);
        Comparator<CensusDAO> comparatorForArea = Comparator.comparing(census -> census.totalArea, Comparator.reverseOrder());
        Comparator<CensusDAO> comparatorForDensity = Comparator.comparing(census -> census.populationDensity, Comparator.reverseOrder());
        Comparator<CensusDAO> comparatorForPopulation = Comparator.comparing(census -> census.population, Comparator.reverseOrder());
        censusMap.put(Parameter.STATE, comparatorForState);
        censusMap.put(Parameter.AREA, comparatorForArea);
        censusMap.put(Parameter.DENSITY, comparatorForDensity);
        censusMap.put(Parameter.POPULATION, comparatorForPopulation);
        return censusMap.get(parameter);
    }
}

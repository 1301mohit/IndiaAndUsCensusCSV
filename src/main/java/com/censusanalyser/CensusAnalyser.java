package com.censusanalyser;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CensusAnalyser {

    enum Country {
        India, US
    }

    Country country;

    Map<String, CensusDAO> censusStateMap = null;

    public CensusAnalyser() {
    }

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusStateMap = CensusAdapterFactory.createCensusAdapter(country).loadCensusData(country, csvFilePath);
        return censusStateMap.size();
    }

    public String getSortedCensusData(ParameterForSort.Parameter parameter) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = ParameterForSort.getCensusMap(parameter);
        return this.getSort(censusComparator);
    }

    private String getSort(Comparator<CensusDAO> censusComparator) {
        System.out.println(censusComparator);
        List<Object> censusList = censusStateMap.values().stream().
                sorted(censusComparator).
                map(census -> census.getCensesCSV(country)).
                collect(Collectors.toList());
        String sortedCensusJson = new Gson().toJson(censusList);
        return sortedCensusJson;
    }

    public String sortByPopulationAndDensity() throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<CensusDAO> censusDAOS = censusStateMap.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> comparatorForPopulation = ParameterForSort.getCensusMap(ParameterForSort.Parameter.POPULATION);
        Comparator<CensusDAO> comparatorForDensity = ParameterForSort.getCensusMap(ParameterForSort.Parameter.DENSITY);
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CensusDAO census1 = censusDAOS.get(j);
                CensusDAO census2 = censusDAOS.get(j + 1);
                if (comparatorForPopulation.compare(census1, census2) > 0) {
                    censusDAOS.set(j, census2);
                    censusDAOS.set(j+1, census1);
                }
                else if(comparatorForPopulation.compare(census1, census2) == 0){
                    if(comparatorForDensity.compare(census1, census2) > 0) {
                        censusDAOS.set(j, census2);
                        censusDAOS.set(j + 1, census1);
                    }
                }
            }
        }
        List censusList =  censusDAOS.stream().map(census -> census.getCensesCSV(country)).collect(Collectors.toList());
        String sortedCensusJson = new Gson().toJson(censusList);
        return sortedCensusJson;
    }

}
package com.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class USCensusAdapterTest {

    private static final String US_CENSUS_FILE_PATH = "/home/mohit/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> censusStateMap = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.India, US_CENSUS_FILE_PATH);
            Assert.assertEquals(29, censusStateMap.size());
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(ParameterForSort.Parameter.POPULATION);
            USCensusCode[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCode[].class);
            Assert.assertEquals(37253956, censusCSV[0].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(ParameterForSort.Parameter.DENSITY);
            USCensusCode[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCode[].class);
            Assert.assertEquals(3805.61, censusCSV[0].populationDensity, 0.01);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(ParameterForSort.Parameter.AREA);
            USCensusCode[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCode[].class);
            Assert.assertEquals(1723338.01, censusCSV[0].totalArea,0.01);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(ParameterForSort.Parameter.STATE);
            USCensusCode[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCode[].class);
            Assert.assertEquals("Alabama", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}

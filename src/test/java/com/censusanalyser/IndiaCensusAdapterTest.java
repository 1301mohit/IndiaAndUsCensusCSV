package com.censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IndiaCensusAdapterTest {
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";
    private static final String DELIMITER_FILE_PATH = "./src/test/resources/DelimiterWrong.csv";
    private static final String HEADER_FILE_PATH = "./src/test/resources/HeaderMissing.csv";
    private static final String US_CENSUS_FILE_PATH = "/home/admin1/Desktop/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> censusStateMap = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.India, INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFile_ReturnsCorrectRecords() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> censusStateMap = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.India, INDIA_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusDataAndStateCodeCSVFile_ReturnsCorrectRecords() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> censusStateMap = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, censusStateMap.size());
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> censusStateMap = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.India, WRONG_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}

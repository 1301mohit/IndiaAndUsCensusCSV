package com.censusanalyser;

public class CensusAdapterFactory {

    public static CensusAdapter createCensusAdapter(CensusAnalyser.Country country) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.India))
            return new IndiaCensusAdapter();
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter();
        throw new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.NO_SUCH_COUNTRY);

    }
}

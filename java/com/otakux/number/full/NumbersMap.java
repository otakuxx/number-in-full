package com.otakux.number.full;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class NumbersMap {

    private String name;
    private String language;
    private String gender;

    public NumbersMap(String name, String language, String gender) {
        this.name = name;
        this.language = language;
        this.gender = gender;
    }

    public Map<Long, NumberSet> map(NumberSet... args) {
        Map<Long, NumberSet> map = new LinkedHashMap<>();
        for (NumberSet numberSet : args) {
            map.put(numberSet.getValue(), numberSet);
        }
        return map;
    }

    public boolean includElementName (long base, long element ){
        return !excludeElementName ( base, element );
    }

    protected abstract boolean excludeElementName(long base, long element);



    public abstract NumberSet get( long number );

    public abstract long getStartBase();

    public abstract long getStartClass();

    public abstract String getConcatAnd();

    public abstract String getConcatSeparator();

    protected NumberSet set(long value, String name) {
        return new NumberSet( value, name );
    }

}

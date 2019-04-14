package com.otakux.number.full;

import java.util.LinkedList;
import java.util.List;

public class NumberSet {

    private long value;
    private String name;
    private String complexName;
    private String className;

    private List< Long > excluds;

    public NumberSet(long value, String name, String complexName) {
        this.value = value;
        this.name = name;
        this.complexName = complexName;
        this.excluds = new LinkedList<>();
    }



    public NumberSet(long value, String name ) {
        this.value = value;
        this.name = name;
        this.excluds = new LinkedList<>();
    }

    public NumberSet complexName( String complexName ){
        this.complexName = complexName;
        return this;
    }


    public long getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public NumberSet excludeNameOfAlgarismo(long ... excludes ){
        for ( long exclude: excludes )
            if( exclude >= 0 && exclude < 10 ) this.excluds.add( exclude );
        return this;
    }

    public NumberSet className( String className ){
        this.className = className;
        return this;
    }

    /**
     * Verificar se o algarismo pode ser incluido para o processo
     * @param element
     * @return
     */
    public boolean excludeElement( long element ) {
        return this.excluds.contains( element );
    }

    public String getComplexName() {
        if( this.complexName == null ) return this.name;
        return complexName;
    }




    @Override
    public String toString() {
        return this.name;
    }
}

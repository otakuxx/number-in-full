package com.otakux.number.test;

import com.otakux.number.full.NumberReader;
import com.otakux.number.full.NumbersMap;
import com.otakux.number.full.NumbersMapCardinal;
import com.otakux.number.full.NumbersMapOrdinal;

public class MainTest {
    public static void main(String[] args) {

        //NumberText.Element result = numberText.parse(6827000367298783L );

        long value = 92891813;

        // Instantiable mode
        NumbersMap ordinalNumber = new NumbersMapOrdinal();
        NumbersMap cardinalNumber = new NumbersMapCardinal();

        // Reader value in cardinal number
        NumberReader numberReader = new NumberReader( cardinalNumber );
        System.out.println( numberReader.parse( value ).getValueName() );

        //Reader value in ordinal number
        numberReader.setNumbersMap( ordinalNumber );
        System.out.println(  numberReader.parse( value ).getValueName() );



        // Call static mode

        // Reader value in cardinal number
        System.out.println( NumbersMapCardinal.parse( value ) );

        //Reader value in ordinal number
        System.out.println( NumbersMapOrdinal.parse( value ) );

    }

}

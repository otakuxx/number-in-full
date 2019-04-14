package com.otakux.number.full;

import java.util.Map;

public class NumbersMapOrdinal extends NumbersMap {

    private Map< Long, NumberSet> map;

    public NumbersMapOrdinal() {
        super( "Ordinal", "PT", "Male" );

        this.map = map(
            set( 0L, "Zero" ),
            set( 1L, "Primeiro" ),
            set( 2L, "Segundo" ),
            set( 3L, "Terceiro" ),
            set( 4L, "Quarto" ),
            set( 5L, "Quinto" ),
            set( 6L, "Sexto" ),
            set( 7L, "Sétimo" ),
            set( 8L, "Oitavo" ),
            set( 9L, "Nono" ),
            set( 10L, "Décimo" ),
            set( 20L, "Vigésimo" ),
            set( 30L, "Trigésimo" ),
            set( 40L, "Quadragésimo" ),
            set( 50L, "Qüinquagésimo" ),
            set( 60L, "Sexagésimo" ),
            set( 70L, "Septuagésimo" ),
            set( 80L, "Octagésimo" ),
            set( 90L, "Nonagésimo" ),
            set( 100L, "Centésimo" ),
            set( 200L, "Ducentésimo" ),
            set( 300L, "Trecentésimo" ),
            set( 400L, "Quadringentésimo" ),
            set( 500L, "Qüingentésimo" ),
            set( 600L, "Sexcentésimo" ),
            set( 700L, "Septingentésimo" ),
            set( 800L, "Octingentésimo" ),
            set( 900L, "Noningentésimo" ),
            set( 1000L, "Milésimo" ),
            set( 1000000L, "Milionésimo" ),
            set( 1000000000L, "Bilionésimo" ),
            set( 1000000000000L, "Trilionésimo" )
        );
    }

    public static String parse( long value ){
        NumberReader numberText = new NumberReader( new NumbersMapOrdinal() );
        NumberReader.Element result = numberText.parse(value);
        return result.getValueName();
    }

    @Override
    protected boolean excludeElementName(long base, long element) {
        return this.map.get( base ) != null
                && this.map.get( base ).excludeElement( element );
    }

    @Override
    public NumberSet get(long number) {
        return this.map.get( number );
    }

    @Override
    public long getStartBase() {
        return 1000000000000L;
    }

    @Override
    public long getStartClass() {
        return 1;
    }

    @Override
    public String getConcatAnd() {
        return " ";
    }

    @Override
    public String getConcatSeparator() {
        return ", ";
    }

}

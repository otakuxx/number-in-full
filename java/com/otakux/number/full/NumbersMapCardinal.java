package com.otakux.number.full;

import java.util.Map;

public class NumbersMapCardinal extends NumbersMap {

    private Map< Long, NumberSet> map;

    public NumbersMapCardinal() {
        super( "Cardinal", "PT", "Male" );

        this.map = map(
            set( 0L, "Zero" ),
            set( 1L, "Um" ).className( "Unidade" ),
            set( 2L, "Dois" ),
            set( 3L, "Três" ),
            set( 4L, "Quatro" ),
            set( 5L, "Cinco" ),
            set( 6L, "Seis" ),
            set( 7L, "Sete" ),
            set( 8L, "Oito" ),
            set( 9L, "Nove" ),
            set( 10L, "Dez" ).className( "Dezena" ),
            set( 11L, "Onze" ),
            set( 12L, "Doze" ),
            set( 13L, "Treze" ),
            set( 14L, "Catorze" ),
            set( 15L, "Quinze" ),
            set( 16L, "Dezasseis" ),
            set( 17L, "Dezassete" ),
            set( 18L, "Dezoito" ),
            set( 19L, "Dezanove" ),
            set( 20L, "Vinte" ),
            set( 30L, "Trinta" ),
            set( 40L, "Quarenta" ),
            set( 50L, "Cinquenta" ),
            set( 60L, "Sessenta" ),
            set( 70L, "Setenta" ),
            set( 80L, "Oitenta" ),
            set( 90L, "Noventa" ),
            set( 100L, "Cem" ).complexName( "Cento" ) .className( "Centena" ),
            set( 200L, "Duzentos" ),
            set( 300L, "Trezentos" ),
            set( 400L, "Quatrocentos" ),
            set( 500L, "Quinhentos" ),
            set( 600L, "Seiscentos" ),
            set( 700L, "Setecentos" ),
            set( 800L, "Oitocentos" ),
            set( 900L, "Novecentos" ),
            set( 1000L, "Mil" ) .excludeNameOfAlgarismo( 1 ) . className( "Milhar" ),
            set( 1000000L, "Milhão" ),
            set( 1000000000L, "Bilhão" ),
            set( 1000000000000L, "Trilhão" ),
            set( 1000000000000000L, "Quatrilhão" ),
            set( 1000000000000000000L, "Quintilhão" )
        );

        double k = 1000000000000000000L;
    }

    public static String parse( long value ){
        NumberReader numberText = new NumberReader( new NumbersMapCardinal() );
        NumberReader.Element result = numberText.parse( value );
        return result.getValueName();
    }

    @Override
    public boolean excludeElementName(long base, long element) {
        return this.map.get( base ) != null
                && this.map.get( base ).excludeElement( element );
    }

    @Override
    public NumberSet get(long number) {
        return this.map.get( number );
    }

    @Override
    public long getStartBase() {
        return 1000000000000000000L;
    }

    @Override
    public long getStartClass() {
        return 1;
    }

    @Override
    public String getConcatAnd() {
        return " e ";
    }

    @Override
    public String getConcatSeparator() {
        return ", ";
    }

}

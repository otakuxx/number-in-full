package com.otakux.number.full;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class NumberReader {

    private NumbersMap numbersMap;
    private boolean debug;

    public NumberReader(NumbersMap numbersMap) {
        this.numbersMap = numbersMap;
    }

    public void setNumbersMap(NumbersMap numbersMap) {
        this.numbersMap = numbersMap;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public class Element {

        @Expose private String valueName;
        @Expose private long element;
        @Expose private long rest;
        @Expose private long base;
        @Expose private long baseClasss;
        @Expose private long sumInClass;
        @Expose private long value;
        @Expose private String concat;

        private NumbersMap numbersMap;
        private Element parent;
        private Element children;

        @Override
        public String toString() {
            GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            return builder.create().toJson( this );
        }

        public String getValueName() {
            return valueName;
        }



        public NumbersMap getNumbersMap() {
            return numbersMap;
        }

        public Element getParent() {
            return parent;
        }

        public Element getChildren() {
            return children;
        }

        public long getValue() {
            return value;
        }

        public long getRest() {
            return rest;
        }

        public Element getRoot(){
            Element _root = this, _parent = this;
            while ( ( _parent = _parent.getParent() ) != null ){
                _root = _parent;
            }
            return _root;
        }

        public Element getResult ( ){
            Element _result = this, _children = this;
            while ( ( _children = _children.getChildren() ) != null ){
                _result = _children;
            }
            return _result;
        }
    }

    public Element parse( long value ){

        Element _element = new Element();
        _element.base = numbersMap.getStartBase();
        _element.rest = value;
        _element.element = _element.rest / _element.base;
        _element.baseClasss = numbersMap.getStartClass();
        _element.sumInClass = 0;
        _element.value = 0;
        _element.concat = "";
        _element.numbersMap = this.numbersMap;

        if( value >= _element.base ){
            _element.rest = value - _element.base;
            _element.value = _element.base;
            _element.sumInClass = _element.base;
        }

        return iterate(_element, null );
    }

    /**
     * Iterar todas as base da maior a menor
     *  * A proxima base a se iterar sera a base atual dividido por dez enguanto a base atual maior que 10  <br/>
     *  * O proximo rest da iteração (resto) é igual ao resto da vivisão do rest atual pela base atual    <br/>
     *  * A proxima baseClasss a se iterar ser o rest da calse atual dividodo por 10                           <br/>
     *  * * Se o resultado da proxima baseClasss menor que 0 então a baseClasss voltara a baseClasss das centenas (100) <br/>
     *                                                                                                      <br/>
     *                                                                                                      <br/>
     *   <h2>Principal algoritimo</h2>                                                                      <br/>
     *   while( base > 0 ){                                                                                 <p style= "margin: 10 ">
     *     rest %= base;                                                                                     <br/>
     *     base /= 10;                                                                                        <br/>
     *     element = rest / base;                                                                          <br/>
     *     baseClasss /= 10;                                                                                      <br/>
     *     if( baseClasss < 1 ) {                                                                                 <p style= "margin: 10 ">
     *            baseClasss = 100;                                                                               <br/>
     *            sumInClass = 0;                                                                       <br/>
     *                                                                                                        </p>
     *     }                                                                                                  <br/></p>
     *   }                                                                                                    <br/>
     *   sumInClass += base * element;                                                              <br/>
     *   value += base * element;                                                                <br/>
     *
     *
     *  @param _element
     * @param text
     * @return
     */
    private Element iterate(Element _element, String text  ) {
        if (text == null) text = "";

        if (debug) System.out.println("Iterate: " + _element);

        Element _next = null;
        String classConcat = " ";
        if (_element.base/10  > 0 ) _next = next(_element);

        if (debug) System.out.println("Next: " + _next);

        /* Quando o numero for de zero absuluto (element 0 da base 1 da baseClasss 1 sem nada acomulado até então) */
        if( _element.element == 0 && _element.baseClasss == 1 && _element.base == 1 && _element.value == 0 ) {
            String nextPart = numbersMap.get( _element.element).getName();
            text = append( _element, text, nextPart, "" );
            if (debug) System.err.println("InIf(1): " + _element);

        /* Quando estiver perante os numeros das casas dos dez (numeros variados entre os 10 a 19)
            Se existir o mapeamentos desses valores no numbersMap enão processar esse rest isto é:
                Quando o rest do proximo algarismos + 10 existirem no mapeamento
        */
        } else if( _element.baseClasss == 10 && _element.element == 1 && _next != null && numbersMap.get( _next.element +10 ) != null ) {
            _element = _next;

            String nextPart = numbersMap.get( _element.element +10 ).getName();
            text = append( _element,  text, nextPart, _element.concat);

            if (debug) System.err.println("InIf(2): " + _element);

        /* Para os algarismos maiores que zero e não for para excluir o rest da base para o element atual
            * ex: escluir o rest do element 1 para a base 1000 (isto é em vez de Um Mil seria apenas Mil )
        * */
        //    inClass: {"valueName":"Um Milhão Um Mil","element":1,"rest":1942,"base":1000,"baseClasss":1,"sumInClass":1000,"value":1001000,"concat":" "}
        } else if( ( _element.element > 0 && numbersMap.includElementName( _element.base, _element.element ) ) || ( _element.element > 0 && _element.sumInClass > _element.base * 2 ) ){

            String nextPart;
            if( _element.baseClasss == 100 && _next != null && ( _next.element > 0 || next( _next ).element > 0 ) ) {
                nextPart = numbersMap.get( _element.element * _element.baseClasss).getComplexName();
            } else {
                nextPart = numbersMap.get( _element.element * _element.baseClasss).getName();
            }
            text = append( _element, text, nextPart, _element.concat);
            if (debug) System.err.println("InIf(3): " + _element);

        } else if( _element.element > 0 ) {
            classConcat = _element.concat;
        }


        if( debug ) System.out.println( "toClass: "+ _element);
        if( _element.base >= 1000 && numbersMap.get( _element.base ) != null && _element.sumInClass >= _element.base ){
            String classe = numbersMap.get( _element.base ).getComplexName();
            text = append( _element, text, classe, classConcat );
            if( debug ) System.err.println( "inClass: "+ _element);

        }

        _element.valueName = text;

        if( _element.base/10  > 0 ){
            return iterate( next(_element), text );
        }

        _element.rest = 0;
        return _element;
    }

    private Element next (Element _element) {
        Element _next = new Element();
        _next.rest = _element.rest % _element.base;
        _next.base =  _element.base / 10;

        _next.element = _next.rest / _next.base;
        _next.baseClasss = _element.baseClasss / 10;
        _next.sumInClass = _element.sumInClass;
        _next.value = _element.value;

        _next.concat = numbersMap.getConcatSeparator();
        if( _next.baseClasss < 1 ){
            _next.baseClasss = 100;
            _next.sumInClass = 0;

        }

        if( _next.sumInClass > 0  )
            _next.concat = numbersMap.getConcatAnd();

        _next.sumInClass += _next.element * _next.base;
        _next.value += _next.element * _next.base;
        _next.parent = _element;
        _element.children = _next;
        _next.numbersMap = this.numbersMap;
        return _next;
    }

    private String append(Element _element, String text, String parte, String concator){
        _element.concat = concator;

        if( text == null ) text =   parte;
        else if ( text.length() == 0 ) text = parte;
        else text += concator + parte;
        _element.valueName = text;
        return  text;
    }
}

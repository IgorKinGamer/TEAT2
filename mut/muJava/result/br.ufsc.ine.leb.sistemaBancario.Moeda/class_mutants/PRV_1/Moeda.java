// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public enum Moeda
{
    BRL( "R$", 100 ),
    USD( "$", 100 ),
    CHF( "Fr", 100 );

    private java.lang.String simbolo;

    private java.lang.Integer baseFracionaria;

    private Moeda( java.lang.String simbolo, java.lang.Integer escala )
    {
        this.simbolo = simbolo;
        this.baseFracionaria = baseFracionaria;
    }

    public  java.lang.String obterSimbolo()
    {
        return simbolo;
    }

    public  java.lang.Integer obterBaseFracionaria()
    {
        return baseFracionaria;
    }

}
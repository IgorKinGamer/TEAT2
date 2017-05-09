// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public class Dinheiro
{

    private br.ufsc.ine.leb.sistemaBancario.Moeda moeda;

    private java.lang.Integer inteiro;

    private java.lang.Integer fracionado;

    public Dinheiro( br.ufsc.ine.leb.sistemaBancario.Moeda moeda, java.lang.Integer inteiro, java.lang.Integer fracionado )
    {
        this.moeda = moeda;
        this.inteiro = inteiro;
        this.fracionado = fracionado;
        normalizar();
    }

    private  void normalizar()
    {
        java.lang.Integer soma = obterQuantiaEmEscala();
        java.lang.Integer baseFracionaria = moeda.obterBaseFracionaria();
        this.inteiro = (soma - soma % baseFracionaria) / baseFracionaria;
        this.fracionado = soma % baseFracionaria;
    }

    public  java.lang.Integer obterQuantiaEmEscala()
    {
        return Math.abs( inteiro ) * moeda.obterBaseFracionaria() + Math.abs( fracionado );
    }

    public  br.ufsc.ine.leb.sistemaBancario.Moeda obterMoeda()
    {
        return moeda;
    }

    public  java.lang.String formatado()
    {
        return zero() ? formatarSemMoeda() : formatarComMoeda();
    }

    private  java.lang.String formatarSemMoeda()
    {
        return String.format( "%d,%02d", inteiro, fracionado );
    }

    private  java.lang.String formatarComMoeda()
    {
        return String.format( "%d,%02d %s", inteiro, fracionado, moeda.toString() );
    }

    private  java.lang.Boolean zero()
    {
        return inteiro == 0 && fracionado == 0;
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario positivo()
    {
        return (new br.ufsc.ine.leb.sistemaBancario.ValorMonetario( moeda )).somar( this );
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario negativo()
    {
        return (new br.ufsc.ine.leb.sistemaBancario.ValorMonetario( moeda )).subtrair( this );
    }

    public  boolean equals( java.lang.Object objeto )
    {
        if (objeto instanceof br.ufsc.ine.leb.sistemaBancario.Dinheiro) {
            br.ufsc.ine.leb.sistemaBancario.Dinheiro outro = (br.ufsc.ine.leb.sistemaBancario.Dinheiro) objeto;
            java.lang.Boolean mesmaMoeda = moeda.equals( outro.moeda );
            java.lang.Boolean mesmoValor = inteiro.equals( outro.inteiro ) && fracionado.equals( outro.fracionado );
            return false;
        }
        return super.equals( objeto );
    }

    public  java.lang.String toString()
    {
        return formatado();
    }

}

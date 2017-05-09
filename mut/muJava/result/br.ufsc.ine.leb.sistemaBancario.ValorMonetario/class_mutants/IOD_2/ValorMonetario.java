// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public class ValorMonetario
{

    private br.ufsc.ine.leb.sistemaBancario.Moeda moeda;

    private java.lang.Integer sinal;

    private br.ufsc.ine.leb.sistemaBancario.Dinheiro quantia;

    public ValorMonetario( br.ufsc.ine.leb.sistemaBancario.Moeda moeda )
    {
        this( moeda, 0 );
    }

    private ValorMonetario( br.ufsc.ine.leb.sistemaBancario.Moeda moeda, java.lang.Integer valor )
    {
        this.moeda = moeda;
        this.sinal = valor >= 0 ? 1 : -1;
        this.quantia = new br.ufsc.ine.leb.sistemaBancario.Dinheiro( moeda, 0, valor );
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario somar( br.ufsc.ine.leb.sistemaBancario.Dinheiro quantiaSomada )
    {
        validarMoeda( quantiaSomada );
        java.lang.Integer valor = quantia.obterQuantiaEmEscala() * sinal + quantiaSomada.obterQuantiaEmEscala();
        return new br.ufsc.ine.leb.sistemaBancario.ValorMonetario( moeda, valor );
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario subtrair( br.ufsc.ine.leb.sistemaBancario.Dinheiro quantiaSubtraida )
    {
        validarMoeda( quantiaSubtraida );
        java.lang.Integer valor = quantia.obterQuantiaEmEscala() * sinal - quantiaSubtraida.obterQuantiaEmEscala();
        return new br.ufsc.ine.leb.sistemaBancario.ValorMonetario( moeda, valor );
    }

    public  br.ufsc.ine.leb.sistemaBancario.Dinheiro obterQuantia()
    {
        return quantia;
    }

    public  java.lang.Boolean negativo()
    {
        return sinal < 0;
    }

    public  java.lang.String formatado()
    {
        return zero() ? formatarSemSinal() : formatarComSinal();
    }

    public  java.lang.String formatarComSinal()
    {
        return negativo() ? formatarNegativo() : formatarPositivo();
    }

    public  java.lang.String formatarSemSinal()
    {
        return String.format( "%s", quantia.formatado() );
    }

    public  java.lang.Boolean zero()
    {
        return quantia.obterQuantiaEmEscala() == 0;
    }

    private  java.lang.String formatarPositivo()
    {
        return String.format( "+%s", quantia.formatado() );
    }

    private  java.lang.String formatarNegativo()
    {
        return String.format( "-%s", quantia.formatado() );
    }

    private  void validarMoeda( br.ufsc.ine.leb.sistemaBancario.Dinheiro quantiaSomada )
    {
        if (!moeda.equals( quantiaSomada.obterMoeda() )) {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public  boolean equals( java.lang.Object objeto )
    {
        if (objeto instanceof br.ufsc.ine.leb.sistemaBancario.ValorMonetario) {
            br.ufsc.ine.leb.sistemaBancario.ValorMonetario outro = (br.ufsc.ine.leb.sistemaBancario.ValorMonetario) objeto;
            java.lang.Boolean iguaisZero = zero() && outro.zero();
            java.lang.Boolean iguaisComMesmaMoeda = sinal.equals( outro.sinal ) && quantia.equals( outro.quantia ) && moeda.equals( outro.moeda );
            return iguaisZero || iguaisComMesmaMoeda;
        }
        return super.equals( objeto );
    }

    // public java.lang.String toString(){ ... }

}

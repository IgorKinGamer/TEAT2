// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public class Entrada implements br.ufsc.ine.leb.sistemaBancario.Transacao
{

    private br.ufsc.ine.leb.sistemaBancario.Dinheiro quantia;

    public Entrada( br.ufsc.ine.leb.sistemaBancario.Conta conta, br.ufsc.ine.leb.sistemaBancario.Dinheiro quantia )
    {
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario obterValorMonetario()
    {
        return quantia.positivo();
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario contabilizar( br.ufsc.ine.leb.sistemaBancario.ValorMonetario saldo )
    {
        return saldo.somar( quantia );
    }

}

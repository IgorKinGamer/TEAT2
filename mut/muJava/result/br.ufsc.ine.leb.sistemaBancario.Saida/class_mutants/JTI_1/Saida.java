// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public class Saida implements br.ufsc.ine.leb.sistemaBancario.Transacao
{

    private br.ufsc.ine.leb.sistemaBancario.Dinheiro quantia;

    public Saida( br.ufsc.ine.leb.sistemaBancario.Conta conta, br.ufsc.ine.leb.sistemaBancario.Dinheiro quantia )
    {
        this.quantia = this.quantia;
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario obterValorMonetario()
    {
        return quantia.negativo();
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario contabilizar( br.ufsc.ine.leb.sistemaBancario.ValorMonetario saldo )
    {
        return saldo.subtrair( quantia );
    }

}

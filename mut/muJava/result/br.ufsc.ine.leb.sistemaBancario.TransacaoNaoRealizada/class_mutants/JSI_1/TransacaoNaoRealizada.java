// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public class TransacaoNaoRealizada implements br.ufsc.ine.leb.sistemaBancario.Transacao
{

    private static br.ufsc.ine.leb.sistemaBancario.Transacao transacao;

    public TransacaoNaoRealizada( br.ufsc.ine.leb.sistemaBancario.Transacao transacao )
    {
        this.transacao = transacao;
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario obterValorMonetario()
    {
        return transacao.obterValorMonetario();
    }

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario contabilizar( br.ufsc.ine.leb.sistemaBancario.ValorMonetario saldo )
    {
        return saldo;
    }

}

// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public interface Transacao
{

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario obterValorMonetario();

    public  br.ufsc.ine.leb.sistemaBancario.ValorMonetario contabilizar( br.ufsc.ine.leb.sistemaBancario.ValorMonetario saldo );

}

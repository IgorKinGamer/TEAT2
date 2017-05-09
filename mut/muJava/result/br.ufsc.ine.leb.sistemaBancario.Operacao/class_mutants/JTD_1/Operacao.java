// This is a mutant program.
// Author : ysma

package br.ufsc.ine.leb.sistemaBancario;


public class Operacao
{

    private br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao estado;

    public Operacao( br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao estado, br.ufsc.ine.leb.sistemaBancario.Transacao... transacoes )
    {
        estado = estado;
    }

    public  br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao obterEstado()
    {
        return estado;
    }

}

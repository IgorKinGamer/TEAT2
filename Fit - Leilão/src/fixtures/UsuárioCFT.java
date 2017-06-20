package fixtures;

import fit.*;
import modelo.*;

public class UsuárioCFT extends ColumnFixture
{
	public String nome, endereco, email, cpf;

	private FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	public boolean cadastrarUsuario()
	{
		try
		{
			fachada.cadastrarUsuario(nome, endereco, email, cpf);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

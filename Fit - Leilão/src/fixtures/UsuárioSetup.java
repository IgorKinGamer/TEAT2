package fixtures;

import fitlibrary.*;
import modelo.*;

public class UsuárioSetup extends SetUpFixture
{
	public void nomeEnderecoEmailCpf(String nome, String endereco, String email, String cpf)
	{
		try
		{
			FabricaDeMercado fábrica = new FabricaDeMercado();
			MercadoLeilao mercado = new MercadoLeilao();
			mercado.cadastrarUsuario(nome, endereco, email, cpf);
			fábrica.desmontar(mercado);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

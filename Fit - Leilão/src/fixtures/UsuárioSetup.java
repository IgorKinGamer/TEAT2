package fixtures;

import fitlibrary.*;
import modelo.*;

public class Usu�rioSetup extends SetUpFixture
{
	public void nomeEnderecoEmailCpf(String nome, String endereco, String email, String cpf)
	{
		try
		{
			FabricaDeMercado f�brica = new FabricaDeMercado();
			MercadoLeilao mercado = new MercadoLeilao();
			mercado.cadastrarUsuario(nome, endereco, email, cpf);
			f�brica.desmontar(mercado);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

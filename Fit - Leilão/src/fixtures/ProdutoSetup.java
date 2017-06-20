package fixtures;

import java.util.*;

import fitlibrary.*;
import modelo.*;

public class ProdutoSetup extends SetUpFixture
{
	public void nomeDoUsuarioEnderecoEmailCpfNomeDoProdutoDescricaoLanceMinimoDataLimite(
			String nomeDoUsuário, String endereço, String email, String cpf,
			String nomeDoProduto, String descrição, Double lanceMínimo, Date dataLimite )
	{
		try
		{
			FabricaDeMercado fábrica = new FabricaDeMercado();
			MercadoLeilao mercado = new MercadoLeilao();
			mercado.cadastrarUsuario(nomeDoUsuário, endereço, email, cpf);
			mercado.cadastrarProduto(nomeDoProduto, descrição, lanceMínimo, cpf, dataLimite);
			fábrica.desmontar(mercado);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

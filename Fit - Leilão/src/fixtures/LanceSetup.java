package fixtures;

import java.util.*;

import fitlibrary.*;
import modelo.*;

public class LanceSetup extends SetUpFixture
{
	public void nomeDoUsuarioEnderecoEmailCpfNomeDoProdutoDescricaoLanceMinimoDataLimiteNomeDoCompradorEnderecoDoCompradorEmailDoCompradorCpfDoComprador(
			String nomeDoUsuário, String endereço, String email, String cpf,
			String nomeDoProduto, String descrição, Double lanceMínimo, Date dataLimite,
			String nomeDoComprador, String endereçoDoComprador, String emailDoComprador, String cpfDoComprador
			)
	{
		try
		{
			FabricaDeMercado fábrica = new FabricaDeMercado();
			MercadoLeilao mercado = new MercadoLeilao();
			mercado.cadastrarUsuario(nomeDoUsuário, endereço, email, cpf);
			mercado.cadastrarUsuario(nomeDoComprador, endereçoDoComprador, emailDoComprador, cpfDoComprador);
			mercado.cadastrarProduto(nomeDoProduto, descrição, lanceMínimo, cpf, dataLimite);
			fábrica.desmontar(mercado);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

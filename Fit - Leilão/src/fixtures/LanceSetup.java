package fixtures;

import java.util.*;

import fitlibrary.*;
import modelo.*;

public class LanceSetup extends SetUpFixture
{
	public void nomeDoUsuarioEnderecoEmailCpfNomeDoProdutoDescricaoLanceMinimoDataLimiteNomeDoCompradorEnderecoDoCompradorEmailDoCompradorCpfDoComprador(
			String nomeDoUsu�rio, String endere�o, String email, String cpf,
			String nomeDoProduto, String descri��o, Double lanceM�nimo, Date dataLimite,
			String nomeDoComprador, String endere�oDoComprador, String emailDoComprador, String cpfDoComprador
			)
	{
		try
		{
			FabricaDeMercado f�brica = new FabricaDeMercado();
			MercadoLeilao mercado = new MercadoLeilao();
			mercado.cadastrarUsuario(nomeDoUsu�rio, endere�o, email, cpf);
			mercado.cadastrarUsuario(nomeDoComprador, endere�oDoComprador, emailDoComprador, cpfDoComprador);
			mercado.cadastrarProduto(nomeDoProduto, descri��o, lanceM�nimo, cpf, dataLimite);
			f�brica.desmontar(mercado);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

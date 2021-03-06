package br.ufsc.ine.leb.sistemaBancario.testes;

import org.hamcrest.*;

import br.ufsc.ine.leb.sistemaBancario.*;

public class EhConta extends BaseMatcher<Conta>
{
	private final String id, titular, banco, ag�ncia;
	
	public EhConta(String id, String titular, String banco, String ag�ncia)
	{
		this.id = id;
		this.titular = titular;
		this.banco = banco;
		this.ag�ncia = ag�ncia;
	}
	
	@Override
	public boolean matches(Object obj)
	{
		if (!(obj instanceof Conta))
			return false;
		Conta c = (Conta) obj;
		Agencia a = c.obterAgencia();
		
		return c.obterIdentificador().equals(id)
				&& c.obterTitular().equals(titular)
				&& a.obterBanco().obterNome().equals(banco)
				&& a.obterNome().equals(ag�ncia);
	}

	@Override
	public void describeTo(Description desc)
	{
		desc.appendText(
				String.format("Conta %s de %s em %s de %s", id, titular, ag�ncia, banco)
		);
	}

	@Override
	public void describeMismatch(Object item, Description description)
	{
		if (item != null && item instanceof Conta)
		{
			Conta c = (Conta) item;
			Agencia a = c.obterAgencia();
			description.appendText(
					String.format("Conta %s de %s em %s de %s",
							c.obterIdentificador(),
							c.obterTitular(),
							a.obterNome(),
							a.obterBanco().obterNome())
			);
		}
		else
			super.describeMismatch(item, description);
	}
}

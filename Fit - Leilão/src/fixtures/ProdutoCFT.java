package fixtures;

import java.util.*;

import fit.*;
import modelo.*;

public class ProdutoCFT extends ColumnFixture
{
	public String nomeDoProduto, descrição;
	public Double lanceMínimo;
	public String cpf;
	public Date dataLimite;
	
	private FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public boolean produtoCadastrado()
	{
		try
		{
			fachada.cadastrarProduto(nomeDoProduto, descrição, lanceMínimo, cpf, dataLimite);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

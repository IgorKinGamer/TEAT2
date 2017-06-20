package fixtures;

import fit.*;
import modelo.*;

public class Lances extends ColumnFixture
{
	public String nomeDoProduto, cpfDoComprador;
	public Double valorDoLance;

	private FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	public boolean lanceVálido()
	{
		try
		{
			fachada.daLance(nomeDoProduto, cpfDoComprador, valorDoLance);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

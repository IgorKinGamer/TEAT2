package fixtures;

import fit.*;
import modelo.*;

public class MercadoActionFixtureTest extends ActionFixture
{
	private FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public void montarMercado()
	{
		fachada.montarMercado();
	}
	
	public boolean mercadoMontado()
	{
		return fachada.isMercadoMontado();
	}
}

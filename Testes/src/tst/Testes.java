package tst;

import java.math.*;
import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

import org.hamcrest.number.*;
import org.hamcrest.object.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsArrayContaining.*;
import static org.hamcrest.text.IsEmptyString.*;
import static org.hamcrest.text.IsEqualIgnoringCase.*;

/////// EXERCÍCIO 1: Testes com matchers ///////

public class Testes
{
	@Test
	public void stringIgual()
	{
		assertThat("Ei", equalTo("Ei"));
	}
	
	@Test
	public void matcherObjeto()
	{
		List<Long> listaDeLongs = Arrays.asList(new Long(123456789), new Long(0));
		String strEsperada = "[123456789, 0]";
		assertThat(listaDeLongs,
				new HasToString<>(equalTo(strEsperada)));
	}
	
	@Test
	public void matcherNúmero()
	{
		assertThat(new BigDecimal(314),
				new BigDecimalCloseTo(new BigDecimal(315), new BigDecimal(1)));
	}
	
	@Test
	public void matcherTexto()
	{
		assertThat("Olha", equalToIgnoringCase("OLHA"));
	}
	
	@Test
	public void matcherTexto2()
	{
		String texto = "Olha";
		assertThat(texto.substring(texto.length()), isEmptyString());
	}
	
	@Test
	public void matcherArray()
	{
		assertThat(new String[] {"Olha", "que", "legal"},
				hasItemInArray("que"));
	}
	
	// Matcher composto
	@Test
	public void intervaloComplexo()
	{
		double valor = 6.89d;
		double centroIntervaloAceito = 7d, erroIntervaloAceito = .30d;
		double centroSubintervaloRejeitado = 7.08d, erroSubintervaloRejeitado = .11d;
		assertThat(valor, allOf(
				new IsCloseTo(centroIntervaloAceito, erroIntervaloAceito),
				not(new IsCloseTo(centroSubintervaloRejeitado, erroSubintervaloRejeitado))));
	}
}

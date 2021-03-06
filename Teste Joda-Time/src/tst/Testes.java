package tst;

import static org.junit.Assert.*;

import org.joda.time.*;
import org.junit.*;

public class Testes
{
	@Test
	public void criarData()
	{
		final int dia = 14, m�s = 3, ano = 2017; 
		LocalDate catorzeMar�o2017 = new LocalDate(ano, m�s, dia);
		assertEquals(dia, catorzeMar�o2017.getDayOfMonth());
		assertEquals(DateTimeConstants.MARCH, catorzeMar�o2017.getMonthOfYear());
		assertEquals(ano, catorzeMar�o2017.getYear());
	}
	
	@Test(expected = IllegalFieldValueException.class)
	public void criarDataInv�lida()
	{
		final int dia = -14, m�s = 3, ano = 2017; 
		new LocalDate(ano, m�s, dia);
	}
	
	@Test(expected = IllegalFieldValueException.class)
	public void anoN�oBissextoN�oTem29DeFevereiro()
	{
		final int dia = 29, m�s = 2, ano = 2001;
		new LocalDate(ano, m�s, dia);
	}
	
	@Test
	public void hor�rioMeioDiaExiste()
	{
		final int hora = 12, minuto = 0, segundo = 0;
		LocalTime meioDia = new LocalTime(hora, minuto, segundo);
		assertEquals(hora, meioDia.getHourOfDay());
		assertEquals(minuto, meioDia.getMinuteOfHour());
		assertEquals(segundo, meioDia.getSecondOfMinute());
		assertEquals(0, meioDia.getMillisOfSecond());
	}
	
	@Test
	public void �ltimaMilissegundoDoDiaExiste()
	{
		final int hora = 23, minuto = 59, segundo = 59, milissegundo = 999;
		LocalTime horaFinal = new LocalTime(hora, minuto, segundo, milissegundo);
		assertEquals(hora, horaFinal.getHourOfDay());
		assertEquals(minuto, horaFinal.getMinuteOfHour());
		assertEquals(segundo, horaFinal.getSecondOfMinute());
		assertEquals(milissegundo, horaFinal.getMillisOfSecond());
	}
	
	@Test
	public void construtoresEquivalentes()
	{
		LocalTime hora = new LocalTime(19, 0);
		LocalTime hora_esperada = new LocalTime(19, 0, 0);
		assertEquals(hora_esperada, hora);
	}
	
	@Test
	public void somaMinutoAoMeioDia()
	{
		LocalTime meioDia = new LocalTime(12, 0);
		LocalTime meioDiaEUm = new LocalTime(12, 1);
		assertEquals(meioDiaEUm, meioDia.plusMinutes(1));
	}
	
	@Test
	public void subtraiMilissegundoDoMeioDia()
	{
		LocalTime meioDia = new LocalTime(12, 0);
		LocalTime meioDiaMenosUmMilissegundo = new LocalTime(11, 59, 59, 999);
		assertEquals(meioDiaMenosUmMilissegundo, meioDia.minusMillis(1));
	}
	
	@Test
	public void milissegundoDaVirada()
	{
		final int hora = 23, minuto = 59, segundo = 59, milissegundo = 999;
		LocalTime horaFinal = new LocalTime(hora, minuto, segundo, milissegundo);
		LocalTime horaZero = new LocalTime(0, 0);
		assertEquals(horaZero, horaFinal.plusMillis(1));
	}
	
	@Test
	public void hor�rioPrecede()
	{
		final int horaManh� = 7, horaTarde = horaManh� + 12, minuto = 23;
		LocalTime momentoManh� = new LocalTime(horaManh�, minuto);
		LocalTime momentoTarde = new LocalTime(horaTarde, minuto);
		assertTrue(momentoManh�.isBefore(momentoTarde));
	}
	
	@Test
	public void hor�rioSucede()
	{
		final int horaManh� = 8, horaTarde = horaManh� + 12, minuto = 5;
		LocalTime momentoManh� = new LocalTime(horaManh�, minuto);
		LocalTime momentoTarde = new LocalTime(horaTarde, minuto);
		assertTrue(momentoTarde.isAfter(momentoManh�));
	}
	
	@Test
	public void dataHor�rioQualquer()
	{
		final int ano = 1997, m�s = 4, dia = 21, hora = 15, minuto = 7, segundo = 58;
		DateTime hora23DeAbrilDe1997 =
				new DateTime(ano, m�s, dia, hora, minuto, segundo);
		assertEquals(ano, hora23DeAbrilDe1997.getYear());
		assertEquals(m�s, hora23DeAbrilDe1997.getMonthOfYear());
		assertEquals(dia, hora23DeAbrilDe1997.getDayOfMonth());
		assertEquals(hora, hora23DeAbrilDe1997.getHourOfDay());
		assertEquals(minuto, hora23DeAbrilDe1997.getMinuteOfHour());
		assertEquals(segundo, hora23DeAbrilDe1997.getSecondOfMinute());
	}
	
	@Test
	public void intervalo()
	{
		DateTime data1 = new DateTime(2010, 3, 4, 8, 51, 10);
		DateTime data2 = new DateTime(2011, 1, 7, 12, 0, 1);
		Interval intervalo = new Interval(data1, data2);
		assertEquals(data1, intervalo.getStart());
		assertEquals(data2, intervalo.getEnd());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void intervaloInv�lido()
	{
		DateTime data1 = new DateTime(2010, 3, 4, 8, 51, 10);
		DateTime data2 = new DateTime(2011, 1, 7, 12, 0, 1);
		new Interval(data2, data1);
	}
	
	@Test
	public void intervalosSobrepostos()
	{
		DateTime data1 = new DateTime(2010, 10, 20, 18, 45);
		DateTime data2 = new DateTime(2010, 10, 30, 18, 45);
		DateTime data3 = new DateTime(2010, 11, 20, 18, 45);
		DateTime data4 = new DateTime(2011, 1, 1, 12, 0, 0);
		Interval intervalo1 = new Interval(data1, data3);
		Interval intervalo2 = new Interval(data2, data4);
		assertTrue(intervalo1.overlaps(intervalo2));
	}
	
	@Test
	public void intervalosSeparados()
	{
		DateTime data1 = new DateTime(2010, 10, 20, 18, 45);
		DateTime data2 = new DateTime(2010, 10, 30, 18, 45);
		DateTime data3 = new DateTime(2010, 11, 20, 18, 45);
		DateTime data4 = new DateTime(2011, 1, 1, 12, 0, 0);
		Interval intervalo1 = new Interval(data1, data2);
		Interval intervalo2 = new Interval(data3, data4);
		assertFalse(intervalo1.overlaps(intervalo2));
		assertFalse(intervalo1.abuts(intervalo2));
	}
	
	@Test
	public void criarPer�odo()
	{
		Period per�odo = new Period(2, 3, 7, 5, 8, 2, 1, 9);
		assertEquals(2, per�odo.getYears());
		assertEquals(3, per�odo.getMonths());
		assertEquals(7, per�odo.getWeeks());
		assertEquals(5, per�odo.getDays());
		assertEquals(8, per�odo.getHours());
		assertEquals(2, per�odo.getMinutes());
		assertEquals(1, per�odo.getSeconds());
		assertEquals(9, per�odo.getMillis());
	}
	
	@Test
	public void anoBissexto�MaisLongo()
	{
		Period umAno = new Period(1, 0, 0, 0, 0, 0, 0, 0);
		DateTime ano2016 = new DateTime(2016, 1, 1, 0, 0);
		DateTime ano2001 = new DateTime(2001, 1, 1, 0, 0);
		Duration dura��oAnoBissexto = umAno.toDurationFrom(ano2016);
		Duration dura��oAnoN�oBissexto = umAno.toDurationFrom(ano2001);
		assertTrue(dura��oAnoBissexto.isLongerThan(dura��oAnoN�oBissexto));
	}
}

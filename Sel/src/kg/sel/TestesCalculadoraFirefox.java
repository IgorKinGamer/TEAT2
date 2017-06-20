package kg.sel;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

public class TestesCalculadoraFirefox
{
	WebDriver d;
	
	@Before
	public void inicializar()
	{
		System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
		d = new FirefoxDriver();
		d.navigate().to("http://www.calculatoria.com");
	}
	
	@Test
	public void multiplicação20x4()
	{
		clicar("2", "0", "×", "4", "=");
		assertEquals("80", d.findElement(By.name("exprdisplay")).getAttribute("value"));
	}
	
	@Test
	public void divisão100por3()
	{
		clicar("1", "0", "0", "÷", "3", "=");
		assertEquals("33.333", d.findElement(By.name("exprdisplay")).getAttribute("value"));
	}
	
	@Test
	public void divisão5por2()
	{
		clicar("5", "÷", "2", "=");
		By deletar = By.cssSelector("span.bdel");
		clicar(deletar, deletar);
		clicar("+", "4", "=");
		assertEquals("6", d.findElement(By.name("exprdisplay")).getAttribute("value"));
	}
	
	@After
	public void finalizar()
	{
		d.quit();
	}
	
	private void clicar(String... botão)
	{
		for (String b : botão)
			d.findElement(By.linkText(b)).click();
	}
	
	private void clicar(By... bs)
	{
		for (By b : bs)
			d.findElement(b).click();
	}
}

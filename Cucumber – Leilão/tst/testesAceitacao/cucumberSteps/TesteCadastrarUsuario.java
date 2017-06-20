package testesAceitacao.cucumberSteps;

import static org.junit.Assert.*;

import cucumber.api.java.en.*;
import modelo.*;

public class TesteCadastrarUsuario
{
	public String nome = "", endereço = "", cpf = "", email = "";
	public boolean deveCadastrar;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	@Given("^O nome de usuario \"([^\"]*)\"$")
	public void o_nome_de_usuario(String n) throws Throwable
	{
		nome = n;
	}

	@Given("^o endereco \"([^\"]*)\"$")
	public void o_endereco(String e) throws Throwable
	{
		endereço = e;
	}

	@Given("^e o CPF \"([^\"]*)\"$")
	public void e_o_CPF(String c) throws Throwable
	{
		cpf = c;
	}

	@Given("^e o e-mail \"([^\"]*)\"$")
	public void e_o_e_mail(String e) throws Throwable
	{
		email = e;
	}

	@When("^O usuario nao existir anteriormente$")
	public void o_usuario_nao_existir_anteriormente() throws Throwable
	{
		deveCadastrar = true;
		try
		{
			deveCadastrar = (fachada.getUsuarioPor(cpf) == null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Then("^O sistema deve cadastrar o usuario com sucesso$")
	public void o_sistema_deve_cadastrar_o_usuario_com_sucesso() throws Throwable
	{
		boolean cadastrou = false;
		
		try
		{
			fachada.cadastrarUsuario(nome, endereço, email, cpf);
			cadastrou = true;
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}
		
		assertEquals(deveCadastrar, cadastrou);
	}
}

package testesAceitacao;

import org.junit.runner.*;

import cucumber.api.*;
import cucumber.api.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "testesAceitacao.cucumberSteps", features = "tst/features", monochrome = true)
public class TestRunner
{

}

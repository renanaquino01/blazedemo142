// 1- bibliotecas / imports
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; // biblioteca principal do selenium
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; // biblioteca do chromeDriver

import io.github.bonigarcia.wdm.WebDriverManager;


// 2 - classe
public class PassagemTest {
    // 2.1 - atributos
    private WebDriver driver; // objeto do selenium

    // 2.2 - Funções e Métodos

    // antes do teste
    @BeforeEach
    public void iniciar() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // instanciar o objeto do selenium como chromeDriver
        driver.manage().window().maximize(); // maximiza a janela do browser
    }

    // depois do teste
    @AfterEach 
    public void finalizar () {
        driver.quit(); // destroi o objeto do selenium
    }

    // teste
    @Test
    public void comprarPassagem() {
        driver.get("https://www.blazedemo.com"); // abre o site balzedemo
        // seleciona origem, destino e aperta o botão "find flights"

        // combo origem
        driver.findElement(By.name("fromPort")).click(); // clica no combo
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'São Paolo']")).click();
        }

        // combo destino
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.click(); // clica no combo
            dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();

        }

        // clicar no botão
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // transição de página
        
        //verificar pagina correta e texto
        assertEquals("Flights from São Paolo to Cairo:", 
            driver.findElement(By.cssSelector("h3")).getText());

        // clica no botão do voo desejado
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();;

        // verifica se esta na pagina de compra
        assertEquals("Your flight from TLV to SFO has been reserved.", 
            driver.findElement(By.cssSelector("h2")).getText());



    }   // final do comprarPassagem
    
} // final da classe PassagemTest
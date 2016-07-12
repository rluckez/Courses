package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.ContaCorrente;
import source.SaldoInsuficienteException;
import source.SenhaInvalidaException;

public class ContaCorrenteTest {

	private ContaCorrente cc; 
	
	@Before
	public void init(){
		cc = new ContaCorrente("12321", "password");
	}
	
	@Test
	public void testGetSaldoInicial(){
		assertEquals(0, cc.getSaldo());
	}
	
	@Test
	public void testDepositar(){
		cc.depositar(100);
		assertEquals(100, cc.getSaldo());
	}
	
	@Test
	public void testSacarComSaldo() throws SaldoInsuficienteException{
		cc.depositar(100);
		assertEquals(100, cc.getSaldo());
		cc.sacar(50);
		assertEquals(50, cc.getSaldo());
	}
	
	@Test(expected = SaldoInsuficienteException.class)
	public void testSacarSemSaldo() throws SaldoInsuficienteException{
		cc.depositar(50);
		assertEquals(50, cc.getSaldo());
		cc.sacar(100);
	}
	
	@Test
	public void autenticaSenhaCorreta() throws SenhaInvalidaException{
		assertEquals(true, cc.autentica("password"));
	}
	
	@Test(expected = SenhaInvalidaException.class)
	public void autenticaSenhaInvalida() throws SenhaInvalidaException{
		cc.autentica("worng-password");
	}
	
}

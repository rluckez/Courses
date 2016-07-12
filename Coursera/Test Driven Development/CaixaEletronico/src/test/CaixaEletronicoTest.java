package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import source.CaixaEletronico;
import source.ContaCorrente;
import source.HardwareQueFalha;
import source.MockHardware;
import source.MockServicoRemoto;

public class CaixaEletronicoTest {

	private CaixaEletronico ce;
	
	@Before
	public void init(){
		ce = new CaixaEletronico();
		ce.setHardware(new MockHardware("12321"));
		ce.setServicoRemoto(new MockServicoRemoto(new ContaCorrente("12321", "password")));
	}
	
	@Test
	public void testLoginComSucesso(){
		assertEquals("Usuário Autenticado", ce.logar("password"));
	}
	
	@Test
	public void testLoginSenhaInvalida(){
		assertEquals("Não foi possível autenticar o usuário", ce.logar("wrong-password"));
	}
	
	@Test
	public void testLoginComFalhaDeHardware(){
		ce.setHardware(new HardwareQueFalha());
		assertEquals("Não foi possível autenticar o usuário", ce.logar("password"));
	}
	
	@Test
	public void testSaldo() {
		ce.logar("password");
		assertEquals("O saldo é R$ 0,00", ce.saldo());;
	}
	
	@Test
	public void testDepositar(){
		ce.logar("password");
		assertEquals("Depósito recebido com sucesso", ce.depositar(100));;
	}
	
	@Test
	public void testDepositarComFalhaDeLeituraDeEnvelope(){
		ce.logar("password");
		ce.setHardware(new HardwareQueFalha());
		assertEquals("Falha na leitura do envelope", ce.depositar(100));;
	}
	
	@Test
	public void testSacarComSaldo(){
		ce.logar("password");
		ce.depositar(100);
		assertEquals("Retire seu dinheiro", ce.sacar(100));;
	}
	
	@Test
	public void testSacarSemSaldo(){
		ce.logar("password");
		ce.depositar(50);
		assertEquals("Saldo insuficiente", ce.sacar(100));;
	}
	
	@Test
	public void testSacarComFalhaDeHardware(){
		ce.logar("password");
		ce.depositar(50);
		ce.setHardware(new HardwareQueFalha());
		assertEquals("Problema ao entregar dinheiro", ce.sacar(50));
	}
	
	
	
	

}

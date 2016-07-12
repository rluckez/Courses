package source;

public abstract class Ponto {

	private String tipo;
	
	public static Ponto builder(String tipo){
		if(tipo.equals(Estrela.ESTRELA)){
			return new Estrela();
		}
		if(tipo.equals(Curtida.CURTIDA)){
			return new Curtida();
		}
		if(tipo.equals(Moeda.MOEDA)){
			return new Moeda();
		}
		throw new RuntimeException();
	}
	
	public Ponto(String tipo){
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}

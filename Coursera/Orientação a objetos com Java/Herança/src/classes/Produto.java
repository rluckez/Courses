package classes;


public class Produto {

	private String nome;
	private int codigo;
	private int preco;
	
	//Construtor
	public Produto(String nome, int codigo, int preco) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.preco = preco;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Produto && ((Produto) obj).getCodigo() == this.getCodigo()){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		//return super.hashCode();
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}
	
}

package classes;

public class ProdutoComTamanho extends Produto {

	private int tamanho;
	
	//Construtor
	public ProdutoComTamanho(String nome, int codigo, int preco, int tamanho) {
		super(nome, codigo, preco);
		this.tamanho = tamanho;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ProdutoComTamanho && ((ProdutoComTamanho) obj).getCodigo() == this.getCodigo()
				&& ((ProdutoComTamanho) obj).getTamanho() == this.getTamanho()){
			return true;
		}else{
			return false;
		}
	}
	
	
	@Override
	public int hashCode() {
//		return super.hashCode();
		return this.getCodigo() + this.getTamanho();
	}

	public int getTamanho() {
		return tamanho;
	}


	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
}

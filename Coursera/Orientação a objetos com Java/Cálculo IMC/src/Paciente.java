
public class Paciente {

	private double height, weight;
	
	public Paciente(double weight, double height){
		this.height = height;
		this.weight = weight;
	}
	
	public double calcularIMC(){
		return weight / (height * height); 
	}
	
	public String diagnostico(){
		double imc = calcularIMC();
		
		if(imc < 16){
			return "Baixo peso muito grave";
		}else if(imc <= 16.99){
			return "Baixo peso grave";
		}else if(imc <= 18.49){
			return "Baixo peso";
		}else if(imc <= 24.99){
			return "Peso normal";
		}else if(imc <= 29.99){
			return "Sobrepeso";
		}else if(imc <= 34.99){
			return "Obesidade grau I";
		}else if(imc <= 39.99){
			return "Obesidade grau II";
		}else{
			return "Obesidade grau III (obesidade mórbida)";
		}
	}
	
}

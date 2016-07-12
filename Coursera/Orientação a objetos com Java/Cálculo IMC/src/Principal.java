
public class Principal {
	
	public static void main(String[] args) {
		
		Paciente p1 = new Paciente(70, 1.60);
		
		Paciente p2 = new Paciente(60, 1.95);
		
		Paciente p3 = new Paciente(95, 1.55);
		
		System.out.println("O IMC do paciente 1 é de: " + p1.calcularIMC() + 
				", portanto ele é considerado como: " + p1.diagnostico());
		
		System.out.println("O IMC do paciente 2 é de: " + p2.calcularIMC() + 
				", portanto ele é considerado como: " + p2.diagnostico());
		
		System.out.println("O IMC do paciente 3 é de: " + p3.calcularIMC() + 
				", portanto ele é considerado como: " + p3.diagnostico());
	}
	
}

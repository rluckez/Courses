
public class Principal {
	
	public static void main(String[] args) {
		
		Paciente p1 = new Paciente(70, 1.60);
		
		Paciente p2 = new Paciente(60, 1.95);
		
		Paciente p3 = new Paciente(95, 1.55);
		
		System.out.println("O IMC do paciente 1 � de: " + p1.calcularIMC() + 
				", portanto ele � considerado como: " + p1.diagnostico());
		
		System.out.println("O IMC do paciente 2 � de: " + p2.calcularIMC() + 
				", portanto ele � considerado como: " + p2.diagnostico());
		
		System.out.println("O IMC do paciente 3 � de: " + p3.calcularIMC() + 
				", portanto ele � considerado como: " + p3.diagnostico());
	}
	
}

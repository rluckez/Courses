package source;

public class Usuario {

	private String name;

	public Usuario(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object userToCompare){
		if(userToCompare != null && userToCompare instanceof Usuario)
			return ((Usuario) userToCompare).getName().equals(this.name);
		return false;
	}
}

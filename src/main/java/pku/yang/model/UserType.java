package pku.yang.model;

public enum UserType {
	Student("0"),Teacher("1");
	
	private String index;
	
	private UserType(String index){
		this.index = index;
	}
	 @Override
	public String toString() {
	    return this.index;
	}


}

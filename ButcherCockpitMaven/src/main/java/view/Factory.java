
public abstract class Factory {

	
	private static Factory factory;
	
	public static Factory get() {
		return factory;
	}
	
	public static void set(Factory factory) {
		Factory.factory = factory;
	}
	
	public abstract void construct();
}

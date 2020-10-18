import view.Cockpit;

public class CockpitFactory extends Factory{

	@Override
	public void construct() {
		new Cockpit();
	}

}

package controller;

import view.Cockpit;

public class MainCockpit {
	public static void main(String[] args) {
		Database.set(new RealDatabase());
		new Cockpit();
	}
}

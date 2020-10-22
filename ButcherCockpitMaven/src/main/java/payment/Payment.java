package payment;

public abstract class Payment {
	
//**ActionListener Zahlungsmethode auswählen 
//Errorhandling und richtige Ausgabe je nach implementierender Klasse

	private static Payment payment;

	public static Payment get() {
		return Payment.payment;
	}

	public static void set(Payment payment) {
		Payment.payment = payment;
	}
	
	//**throws neue Exception?
	public abstract String processPurchase() throws Exception;
	

}

package es.caib.rfhab.logic.utils;

public enum TipusActivitat {

	COPIAAUTENTICA(1),
	TRAMIT(2),
	COMPAREIXENCA(3);
	
	private int valor;
	
	TipusActivitat(int valor) {
		this.valor = valor;
	}
	
	public static TipusActivitat getTipusActivitat(int valor) {
		for (TipusActivitat tipus : TipusActivitat.values()) {
			if (tipus.valor == valor) {
				return tipus;
			}
		}
		return null;
	}
	
}

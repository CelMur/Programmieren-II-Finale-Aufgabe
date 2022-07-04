package de.jprojekt.view;

public enum GuiMessages {
	NOT_IMPLEMENTED_MSG("Diese Funktion ist nicht noch nicht implementiert");
	
	private final String message;
	
	GuiMessages(final String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return this.message;
	}
}

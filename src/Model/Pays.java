package Model;

import java.awt.Color;

public enum Pays {

	Allemagne(new Color(0,0,0),new Color(255,0,0),new Color(255,255,0),"Allemagne"),
	Armeni(new Color(255,0,0),new Color(0,0,255),new Color(255,200,0),"Armenie"),
	Bolivie(new Color(255,0,0),new Color(255,255,0),new Color(9, 154, 38),"Bolivie"),
	Jamaique(new Color(9, 154, 38),new Color(243, 251, 0),Color.red,"Jamaïque"),
	PaysBas(Color.red,new Color(234, 240, 240),Color.blue,"Pays-Bas"),
	Russie(new Color(234, 240, 240), Color.blue, Color.red,"Russie");

	private Color panTop;
	private Color panMid;
	private Color panBot;
	private String name;

	Pays(Color top, Color mid, Color bot, String name) {
		this.panTop = top;
		this.panMid = mid;
		this.panBot = bot;
		this.name = name;
	}
	
	public Color getColorTop() {
		return panTop;
	}

	public Color getColorMid() {
		return panMid;
	}

	public Color getColorBot() {
		return panBot;
	}

	public String getName() {
		return name;
	}

}

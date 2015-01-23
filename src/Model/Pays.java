package Model;

import java.awt.Color;

public enum Pays {

	Allemagne(new Color(0,0,0),new Color(255,0,0),new Color(255,255,0),"Allemagne"),
	Armeni(new Color(255,0,0),new Color(0,0,255),new Color(255,200,0),"Armeni"),
	Bolivie(new Color(255,0,0),new Color(255,255,0),Color.green,"Bolivie"),
	PaysBas(Color.red,Color.WHITE,Color.blue,"Pays-Bas"),
	Russie(Color.WHITE, Color.blue, Color.red,"Russie");
	
	
	
	
	
	private Color panTop;
	private Color panMid;
	private Color panBot;
	private String name;
	
	Pays(Color top,Color mid,Color bot,String name){
		this.panTop=top;
		this.panMid=mid;
		this.panBot=bot;
		this.name=name;
	}
	
	public Color getColorTop(){
		return panTop;
	}
	
	public Color getColorMid(){
		return panMid;
	}
	
	public Color getColorBot(){
		return panBot;
	}
	
	public String getName(){
		return name;
	}
	
}

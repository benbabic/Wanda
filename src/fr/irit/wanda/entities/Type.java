package fr.irit.wanda.entities;

public class Type extends DescribedEntity {

	public Type(int id, String name, String desc) {
		super(id,"type",name,desc);
	}
	
	public Type(String name, String desc) {
		super(-1,"type",name,desc);
	}
}

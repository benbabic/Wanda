package fr.irit.wanda.entities;

public class Rule extends DescribedEntity {

	public Rule(int id, String name, String desc) {
		super(id,"rule",name,desc);
	}
	
	public Rule(String name, String desc) {
		super(-1,"rule",name,desc);
	}
}

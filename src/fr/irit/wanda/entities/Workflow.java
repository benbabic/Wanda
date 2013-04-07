package fr.irit.wanda.entities;

public class Workflow extends DescribedEntity {

	public Workflow(int id, String name, String desc) {
		super(id,"workflow",name,desc);
	}
	
	public Workflow( String name, String desc) {
		super(-1,"workflow",name,desc);
	}
}

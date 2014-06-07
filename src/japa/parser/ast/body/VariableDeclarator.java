/*
 * Copyright (C) 2007 Júlio Vilmar Gesser.
 * 
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 05/10/2006
 */
package japa.parser.ast.body;

import invariants.InvalidValueException;
import invariants.Invariant;
import invariants.Invariants;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import japa.parser.ast.Node;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class VariableDeclarator extends Node {

	private VariableDeclaratorId id;

	private Expression init;

	public VariableDeclarator() {
	}

	public VariableDeclarator(VariableDeclaratorId id) {
		this.id = id;
	}

	public VariableDeclarator(VariableDeclaratorId id, Expression init) {
		this.id = id;
		this.init = init;

		Invariants invs = Invariants.getInstance();
		
		System.out.println("I dectect an initialization!");
		if( init != null ) System.out.println(init.toString());

		if(invs.checkInvariant(id.getName())){
			System.out.println("The target is an invariant! Checking value...");

			Invariant<? extends Comparable<?>> inv = invs.getInvariant(id.getName());
			Integer valuei = null;
			Float valuef = null;
			Character valuec = null;

			boolean isItOk = false;

			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			String newvalue = null;
			try {
				newvalue = engine.eval(init.toString()).toString();
			} catch (ScriptException e1) {
				e1.printStackTrace();
			}

			if(inv.getType().equals("Float")){
				valuef = Float.parseFloat(newvalue);
				isItOk = !invs.checkInvariantValue(id.getName(), valuef);
			} else if(inv.getType().equals("Integer")){
				valuei = Integer.parseInt(newvalue);
				isItOk = !invs.checkInvariantValue(id.getName(), valuei);
			} else if(inv.getType().equals("Character")){
				valuec = init.toString().charAt(0);
				isItOk = !invs.checkInvariantValue(id.getName(), valuec);
			}

			if(isItOk){
				System.out.println("The value its ok!");
			} else {
				System.out.println("The value is not ok!");
				try{
					throw new InvalidValueException();
				} catch( InvalidValueException e){
					e.printStackTrace();
				}
			}
		}
	}

	public VariableDeclarator(int beginLine, int beginColumn, int endLine, int endColumn, VariableDeclaratorId id, Expression init) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.id = id;
		this.init = init;
		
		Invariants invs = Invariants.getInstance();
		
		System.out.println("I dectect an initialization!");
		if( init != null ) System.out.println(init.toString());

		if(invs.checkInvariant(id.getName())){
			System.out.println("The target is an invariant! Checking value...");

			Invariant<? extends Comparable<?>> inv = invs.getInvariant(id.getName());
			Integer valuei = null;
			Float valuef = null;
			Character valuec = null;

			boolean isItOk = false;

			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			String newvalue = null;
			try {
				newvalue = engine.eval(init.toString()).toString();
			} catch (ScriptException e1) {
				e1.printStackTrace();
			}

			if(inv.getType().equals("Float")){
				valuef = Float.parseFloat(newvalue);
				isItOk = !invs.checkInvariantValue(id.getName(), valuef);
			} else if(inv.getType().equals("Integer")){
				valuei = Integer.parseInt(newvalue);
				isItOk = !invs.checkInvariantValue(id.getName(), valuei);
			} else if(inv.getType().equals("Character")){
				valuec = init.toString().charAt(0);
				isItOk = !invs.checkInvariantValue(id.getName(), valuec);
			}

			if(isItOk){
				System.out.println("The value its ok!");
			} else {
				System.out.println("The value is not ok!");
				try{
					throw new InvalidValueException();
				} catch( InvalidValueException e){
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public VariableDeclaratorId getId() {
		return id;
	}

	public Expression getInit() {
		return init;
	}

	public void setId(VariableDeclaratorId id) {
		this.id = id;
	}

	public void setInit(Expression init) {
		this.init = init;
	}

}

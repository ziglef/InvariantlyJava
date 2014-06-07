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
package japa.parser.ast.expr;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import invariants.InvalidValueException;
import invariants.Invariant;
import invariants.Invariants;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class AssignExpr extends Expression {

    public static enum Operator {
        assign, // =
        plus, // +=
        minus, // -=
        star, // *=
        slash, // /=
        and, // &=
        or, // |=
        xor, // ^=
        rem, // %=
        lShift, // <<=
        rSignedShift, // >>=
        rUnsignedShift, // >>>=
    }

    private Expression target;

    private Expression value;

    private Operator op;

    public AssignExpr() {
    }

    public AssignExpr(Expression target, Expression value, Operator op) {
        this.target = target;
        this.value = value;
        this.op = op;
        
        Invariants invs = Invariants.getInstance();
        
        System.out.println("I detect an assignement!");
        if(target != null) System.out.println("Target: "+target.toString());
        if(value != null) System.out.println("Value: "+value.toString());
        
        if(invs.checkInvariant(target.toString())){
        	System.out.println("The target is an invariant! Checking value...");
        	
        	Invariant<? extends Comparable<?>> inv = invs.getInvariant(target.toString());
        	Integer valuei = null;
        	Float valuef = null;
        	Character valuec = null;
        	
        	boolean isItOk = false;
        	
        	ScriptEngineManager mgr = new ScriptEngineManager();
        	ScriptEngine engine = mgr.getEngineByName("JavaScript");
        	String newvalue = null;
        	try {
				newvalue = engine.eval(value.toString()).toString();
			} catch (ScriptException e1) {
				e1.printStackTrace();
			}
        	
        	if(inv.getType().equals("Float")){
        		valuef = Float.parseFloat(newvalue);
            	isItOk = !invs.checkInvariantValue(target.toString(), valuef);
        	} else if(inv.getType().equals("Integer")){
        		valuei = Integer.parseInt(newvalue);
            	isItOk = !invs.checkInvariantValue(target.toString(), valuei);
        	} else if(inv.getType().equals("Character")){
        		valuec = value.toString().charAt(0);
        		isItOk = !invs.checkInvariantValue(target.toString(), valuec);
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

    public AssignExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression target, Expression value, Operator op) {
        super(beginLine, beginColumn, endLine, endColumn);
        this.target = target;
        this.value = value;
        this.op = op;
        
        Invariants invs = Invariants.getInstance();
        
        System.out.println("I detect an assignement!");
        if(target != null) System.out.println("Target: "+target.toString());
        if(value != null) System.out.println("Value: "+value.toString());
        
        if(invs.checkInvariant(target.toString())){
        	System.out.println("The target is an invariant! Checking value...");
        	
        	Invariant<? extends Comparable<?>> inv = invs.getInvariant(target.toString());
        	Integer valuei = null;
        	Float valuef = null;
        	Character valuec = null;
        	
        	boolean isItOk = false;
        	
        	ScriptEngineManager mgr = new ScriptEngineManager();
        	ScriptEngine engine = mgr.getEngineByName("JavaScript");
        	String newvalue = null;
        	try {
				newvalue = engine.eval(value.toString()).toString();
			} catch (ScriptException e1) {
				e1.printStackTrace();
			}
        	
        	if(inv.getType().equals("Float")){
        		valuef = Float.parseFloat(newvalue);
            	isItOk = !invs.checkInvariantValue(target.toString(), valuef);
        	} else if(inv.getType().equals("Integer")){
        		valuei = Integer.parseInt(newvalue);
            	isItOk = !invs.checkInvariantValue(target.toString(), valuei);
        	} else if(inv.getType().equals("Character")){
        		valuec = value.toString().charAt(0);
        		isItOk = !invs.checkInvariantValue(target.toString(), valuec);
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

    public Operator getOperator() {
        return op;
    }

    public Expression getTarget() {
        return target;
    }

    public Expression getValue() {
        return value;
    }

    public void setOperator(Operator op) {
        this.op = op;
    }

    public void setTarget(Expression target) {
        this.target = target;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

}

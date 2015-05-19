package main.java.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ActionPerformer implements ActionListener {
	
	// Properties
	
	private Object target;
	private String action;
	
	// Constructors
	
	public ActionPerformer(Object target, String action) {
		this.target = target;
		this.action = action;
	}
	
	// Getters / setters
	
	public Object getTarget() {
		return this.target;
	}
	
	public String getAction() {
		return this.action;
	}
	
	// ActionListener interface
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Class<?> targetClass = this.target.getClass();
		Method method;
		try {
			method = targetClass.getDeclaredMethod(this.action);
		} catch (NoSuchMethodException exception) {
			System.out.println(exception);
			return;
		}
		try {
			method.invoke(this.target);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception);
		} catch (IllegalAccessException exception) {
			System.out.println(exception);
		} catch (InvocationTargetException exception) {
			System.out.println(exception);
		}
	}	
}

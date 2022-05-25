package de.jprojekt.view.interfaces;

public interface ICommand<R, T> {
	public R execute(T data);
}

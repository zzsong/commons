package com.zss.commons.appmodel.result;

public class ModelResult<T> extends BaseResult {

	private static final long serialVersionUID = 1L;

	private T model;

	public ModelResult() {
	}

	public ModelResult(T model) {
		this.model = model;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}

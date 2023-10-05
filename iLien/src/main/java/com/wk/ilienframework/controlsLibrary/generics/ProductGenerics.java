package com.wk.ilienframework.controlsLibrary.generics;

public class ProductGenerics<X> {

	private X productInfo;

	public ProductGenerics(X x1) {

		productInfo = x1;
	}

	public void setProductInfo(X arg) {

		productInfo = arg;
	}

	public X getProductInfo() {

		return productInfo;
	}

}

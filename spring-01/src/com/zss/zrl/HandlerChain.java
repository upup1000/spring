package com.zss.zrl;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链实现
 * @author zss
 */
public class HandlerChain implements IHandler {
	private List<IHandler> handlers = new ArrayList<IHandler>();

	public void addFirst(IHandler hander) {
		handlers.add(0, hander);
	}

	public void addLast(IHandler hander) {
		handlers.add(hander);
	}

	@Override
	public byte[] doIn(byte[] data) {
		byte[] newdata = data;
		for (IHandler hand : handlers) {
			newdata = hand.doIn(newdata);
		}
		return newdata;
	}

	@Override
	public byte[] doOut(byte[] data) {
		byte[] newdata = data;
		int size=handlers.size();
		for (int i=0;i<size;i++) {
			newdata = handlers.get(size-1-i).doOut(newdata);
		}
		return newdata;
	}

}

package org.springframework.boot.example.spi.dao.impl;

import org.springframework.boot.example.spi.dao.IPayDao;

public class WechatPayDao implements IPayDao {
	@Override
	public void pay() {
		System.out.println("Wechat Pay");
	}
}

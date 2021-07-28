package org.springframework.boot.example.spi;

import org.springframework.boot.example.spi.dao.IPayDao;

import java.util.ServiceLoader;

public class App {
	public static void main(String[] args) {
		ServiceLoader<IPayDao> services = ServiceLoader.load(IPayDao.class);
		for (IPayDao userDao : services) {
			userDao.pay();
		}
	}
}

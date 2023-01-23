package com.springpractice.spring.learnspringframework.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	
	@Autowired
	private GamingConsole game;
	
	public GameRunner(GamingConsole game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	public void run() {
		game.up();
		game.right();
		game.down();
		game.left();
	}

}

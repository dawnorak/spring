package com.springpractice.spring.learnspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.springpractice.spring.learnspringframework.game.GameRunner;
import com.springpractice.spring.learnspringframework.game.GamingConsole;
import com.springpractice.spring.learnspringframework.game.MarioGame;
// import com.springpractice.spring.learnspringframework.game.SuperContaraGame;

@SpringBootApplication
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LearnSpringFrameworkApplication.class, args);
		// MarioGame game = new MarioGame();
		// GamingConsole game = new MarioGame();
		// GameRunner runner = new GameRunner(game);

		GameRunner runner = context.getBean(GameRunner.class);
		runner.run();
	}

}

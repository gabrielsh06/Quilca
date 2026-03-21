package com.quilca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void init() {
		springContext = SpringApplication.run(Main.class);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/main-view.fxml"));
		loader.setControllerFactory(springContext::getBean);
		Scene scene = new Scene(loader.load());
		stage.setTitle("Quilca");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() {
		springContext.close();
	}
}

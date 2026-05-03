package com.quilca;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class Main extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void init() {
		springContext = SpringApplication.run(Main.class);
	}

	@Bean
	public HostServices hostServices() {
		return getHostServices();
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com.quilca/view/main-layout.fxml"));
		loader.setControllerFactory(springContext::getBean);
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(Objects.requireNonNull(getClass()
				.getResource("/com.quilca/styles/editor-styles.css")).toExternalForm());
		scene.getStylesheets().add(Objects.requireNonNull(getClass()
				.getResource("/com.quilca/styles/layout-styles.css")).toExternalForm());
		scene.getStylesheets().add(Objects.requireNonNull(getClass()
				.getResource("/com.quilca/styles/theme.css")).toExternalForm());
		stage.setTitle("Quilca");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

	// Required to create the executable.
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	@Override
	public void stop() {
		springContext.close();
	}
}

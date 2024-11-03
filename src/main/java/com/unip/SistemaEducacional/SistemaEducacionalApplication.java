package com.unip.SistemaEducacional;

import com.unip.SistemaEducacional.dao.StudentDaoImpl;
import com.unip.SistemaEducacional.models.Student;
import com.unip.SistemaEducacional.views.AppMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.unip.SistemaEducacional")
public class SistemaEducacionalApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		ApplicationContext context = SpringApplication.run(SistemaEducacionalApplication.class, args);

		SwingUtilities.invokeLater(() -> {
			try {
				AppMenu appMenu = context.getBean(AppMenu.class);
				appMenu.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}

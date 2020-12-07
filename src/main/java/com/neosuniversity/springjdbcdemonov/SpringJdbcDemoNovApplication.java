package com.neosuniversity.springjdbcdemonov;


import com.neosuniversity.springjdbcdemonov.dao.EmployeeDAO;
import com.neosuniversity.springjdbcdemonov.domain.Employee;
import com.neosuniversity.springjdbcdemonov.respository.EmployeeRepository;


import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.IOException;
import java.util.List;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootApplication
@CommonsLog
public class SpringJdbcDemoNovApplication implements CommandLineRunner {

	@Autowired
	private EmployeeDAO dao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoNovApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("Similar al metodo MAIN");

		Employee emp = new Employee(null,"Mario", "Hidalgo",
				"mario.hidalgo@yahoo.com.mx");

		dao.save(emp);

		List<Employee> list = dao.findAll();

		list.forEach(employee -> System.out.println(employee));


		// actualizacion
		Employee emp2 = new Employee(1,"HUGO", "HIDALGO",
				"hhugo77@gmail.com");

		log.info("---------------------------");
		dao.update(emp2);
		list = dao.findAll();
		list.forEach(employee ->{
			log.info(employee);
		});


		log.info("---------------------------");

		dao.delete(1);

		list = dao.findAll();
		list.forEach(System.out::println);
		
		sendEmail();
		sendEmailWithAttachment();
		log.info("-------EMAIL SUCCESSFUL!!----");
		
		

	}
		
		
	public void sendEmail() {
		
		List<Employee> employees=employeeRepository.findAll();
		 SimpleMailMessage msg = new SimpleMailMessage();
		 
		employees.stream().forEach(employee ->{
			 msg.setTo(employee.getEmail());
		});

        msg.setSubject("Prueba de envio de e-mail");
        msg.setText("Hola Mundo \n Spring Boot Email");

        javaMailSender.send(msg);

    }
	
	 public void sendEmailWithAttachment() throws MessagingException, IOException {

	        MimeMessage msg = javaMailSender.createMimeMessage();

	       
	        MimeMessageHelper helper = new MimeMessageHelper(msg,true);
	        helper.setTo("hhugohm@gmail.com");

	        helper.setSubject("Prueba de envio de e-mail");

	      
	        helper.setText("<h1>Favor de revisar el archivo adjunto a este email!</h1>",true);

	        helper.addAttachment("information.txt", new ClassPathResource("information.txt"));

	        javaMailSender.send(msg);
	    }
}

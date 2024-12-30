package com.diardon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateApp
{
	public static void main(String[] args)
	{
		// Crear la configuración y construir el SessionFactory
		SessionFactory sessionFactory = new Configuration()
				.addAnnotatedClass(User.class) // Agregamos las entidades
				.buildSessionFactory();

		// Abrir una sesión
		Session session = sessionFactory.openSession();

		// Iniciar una transacción
		session.beginTransaction();

		User user1 = new User("Juan", "juan@empresa.com");
		User user2 = new User("Ana", "ana@empresa.com");
		User user3 = new User("Pedro", "pedro@empresa.com");
		User user4 = new User("Maria", "maria@empresa.com");

		// Crear entidades y marcar para guardar en la base de datos
		session.persist(user1);
		session.persist(user2);
		session.persist(user3);
		session.persist(user4);
		
		// Muestro los usarios
		System.out.println("----- Usuarios a escribir -----");
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
		System.out.println(user4);

		// Confirmar la transacción
		session.getTransaction().commit();

		// Recuperar el usuario guardado
		User retrievedUser1 = session.get(User.class, user1.getId());
		User retrievedUser2 = session.get(User.class, user2.getId());
		
		System.out.println("----- Usuarios 1 y 2 leídos -----");
		System.out.println(retrievedUser1);
		System.out.println(retrievedUser2);

		// Cerrar la sesión y el SessionFactory
		session.close();
		sessionFactory.close();
	}
}

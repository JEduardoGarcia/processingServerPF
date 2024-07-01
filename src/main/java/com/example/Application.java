package com.example;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}

    /* Pruebas
    public static void main(String[] args) {
        // Crear una instancia del servicio de búsqueda
        TextSearchService textSearchService = new TextSearchService();

        // Número de palabras en la frase
        int n = 3; // Ejemplo con 3 palabras en las frases
        // Los documentos a procesar están en el rango del 7 al 13
        int startDocumentIndex = 1;
        int endDocumentIndex = 15;

        try {
            // Indexar documentos en el rango específico
            textSearchService.indexDocumentsInRange(startDocumentIndex, endDocumentIndex);

            // Realizar la búsqueda con el número de palabras n
            List<String> fileNames = textSearchService.getFileLoader().listAllFileNames(); // Obtener los nombres de los archivos
            SearchResponse response = textSearchService.performSearch(n, fileNames.subList(startDocumentIndex, endDocumentIndex + 1));

            // Imprimir resultados
            System.out.println("Número de servidores activos: 1"); // Ajusta esto según la configuración real de los servidores
            System.out.println("Tiempo de procesamiento: " + response.getProcessingTime() + " ms");
            for (PhraseMatch match : response.getMatches()) {
                System.out.println("\"" + match.getPhrase() + "\" aparece en " + match.getDocuments());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
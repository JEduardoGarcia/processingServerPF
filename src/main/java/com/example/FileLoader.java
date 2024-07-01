package com.example;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private static final String TEXTS_PATH = "textos_truncados/";

    // Método para listar todos los nombres de los archivos en el directorio
    // especificado
    public List<String> listAllFileNames() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:" + TEXTS_PATH + "*.txt");
        List<String> fileNames = new ArrayList<>();

        for (Resource resource : resources) {
            fileNames.add(resource.getFilename());
        }
        return fileNames;
    }

    // Método para listar los nombres de los archivos en el rango especificado
    public List<String> listFileNames(int start, int end) throws IOException {
        List<String> allFileNames = listAllFileNames();
        return allFileNames.subList(start, end);
    }

    // Método para cargar el contenido de un archivo específico
    public String loadFileContent(String fileName) throws IOException {
        Resource resource = new ClassPathResource(TEXTS_PATH + fileName);
        return IOUtils.toString(resource.getInputStream(), "UTF-8");
    }

    public FileLoader getFileLoader() {
        return this;
    }
}

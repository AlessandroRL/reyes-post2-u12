package com.empresa.pedidos;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ReglasArquitecturaRunner {

    @Test
    void dominio_no_debe_depender_de_infraestructura() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.empresa.pedidos");

        ArchRule dominioAislado = noClasses()
                .that().resideInAPackage("..dominio..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..infraestructura..", "..adaptadores..", "javax.persistence..", "org.springframework.mail..");

        dominioAislado.check(classes);
    }
}

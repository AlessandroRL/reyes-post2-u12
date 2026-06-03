package com.empresa.pedidos.arquitectura;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.empresa.pedidos")
class ArquitecturaCapasTest {

    @ArchTest
    static final ArchRule dominioNoDependeDeInfraestructura = noClasses()
            .that().resideInAPackage("..dominio..")
            .should().dependOnClassesThat().resideInAnyPackage("..infraestructura..", "..adaptadores..", "org.springframework..");

    @ArchTest
    static final ArchRule aplicacionSoloUsaDominioYSpring = classes()
            .that().resideInAPackage("..aplicacion..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                    "java..",
                    "jakarta..",
                    "org.springframework..",
                    "com.empresa.pedidos.dominio..",
                    "com.empresa.pedidos.aplicacion.."
            );
}

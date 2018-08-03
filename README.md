# Mutantes - Examen Mercadolibre
### Introduccion
Se necesita un aplicativo que pueda resolver via una API REST si un ADN es del tipo mutante o humamno. Se pueden 
consultar estadisticas de las busquedas analizadas.
La especificacion se encuentra anexada en la carpeta docs del aplicativo.

### Tecnologias

* Java 1.8
* Spring Boot 2.0.4.RELEASE
* MongoDB
* Maven 3.5
* AWS

### Funcionalidades
####Detector Mutantes: 

endpoint http://mutantes.us-east-2.elasticbeanstalk.com/mutant

POST → /mutant/
{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

En caso de verificar un mutante devuelve HTTP 200-OK, caso contrario 403-Forbidden

Para resolver el algoritmo de busqueda de mutantes se implementó el patrón de diseño Strategy. La idea es poder dejar abierta la posibilidad de implementar otros tipos de busqueda o bien dejar deprecada alguna estrategia especifica por cambios en el negocio de una forma mas practica.

Strategy: Se declara una interfaz común para todos los algoritmos soportados. 
Esta interfaz será usada por el contexto para invocar a la estrategia concreta
* interface SearchMutant

ConcreteStrategy: Implementan el algoritmo utilizando la interfaz definida por la estrategia.
* class SearchDiagonalAsc
* class SearchDiagonalDesc
* class SearchDiagonalHorizontal
* class SearchDiagonalVertical

Para registrar los escaneos realidos por la API se utiliza la BD no relacional MongoDB
https://mlab.com/

Ejemplo documento:

{
    "_id": {
        "$oid": "5b6443df728c611939a5c2b5"
    },
    "className": "com.mercadolibre.mutansexam.model.Dna",
    "sequence": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ],
    "mutant": false
}

####Estadisticas:
 endpoint http://mutantes.us-east-2.elasticbeanstalk.com/stats
 
Se expone un servicio extra /stats que devuelva un Json con las estadísticas de las
verificaciones de ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}

##Deploy
mvn spring-boot:run

##Observaciones
Se generaron features en git por cada nivel especificado, una vez concretado el objetivo se realizó el merge correspondiente.
proximamente voy a estar cerrando subir el mismo proyecto utilizando Google SDK.

En el repo se encuentra el feature/google_engine donde se implementa la misma API pero en el cloud de Google SDK
https://examen-mutantes.appspot.com/



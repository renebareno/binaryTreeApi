# BinaryTreeApi
Prueba técnica consiste en un Api rest, con métodos para crear un árbol binario y buscar ancestros comunes.

##Nodos:

    {
		"value": 39,
		"leftNode": 28,
		"rightNode": 44
	  },
Un nodo tiene su valor y el valor de sus hijos.
##Arbol:

    {
      "id": "tree",
      "tree": [
        {
          "value": 67,
          "leftNode": 39,
          "rightNode": 76
        },
        {
          "value": 39,
          "leftNode": 
           ...
      ]
    }

#Requisitos

* Java 1.8
* Maven

#Como arrancar

* Descargar el codigo fuente 
   *git clone https://github.com/renebareno/binaryTreeApi.git

* Ejecutar:
  * mvn compile && mvn exec:java
* Así se inicia un servidor en http:127.0.0.1:4567

# Uso del Api:

Se exponen tres endpoinds
* post  /newTree
* get   /lowestCommonAncestor
* get   /defaultTree

## defaultTree
Es un metodo que se consume por Get. Retorna un json con el arbol dado en el ejemplo de la prueba.

      [GET]
      http://localhost:4567/defaultTree
      {
        "id": "tree",
        "tree": [
            {
                "value": 67,
                "leftNode": 39,
                "rightNode": 76
            },
            {
                "value": 
            ...

## newTree
Es un metodo que se consume por Post. Permite ingresar un arbol como el definico anteriormente.

      [POST]
      http://localhost:4567/newTree
      {
        "id": "tree",
        "tree": [
            {
                "value": 67,
                "leftNode": 39,
                "rightNode": 76
            },
            {
                "value": 
            ...


## lowestCommonAncestor
Es un metodo que se consume por Get. Permite consultar el ancestro mas cercano de los valores unidicados.  Por query string se le proporcionan los parametros, por ejemplo:  http://localhost:4567/lowestCommonAncestor?treeId=tree&child=83&otherChild=87 

      [POST]
      http://localhost:4567/lowestCommonAncestor?treeId=tree&child=83&otherChild=87
      {
          "value": 85,
          "leftNode": 83,
          "rightNode": 87
       }

## showTrees

Es un metodo que se consume por Get. Retorna un json una arreglo de arboles ingresados.

      [GET]
      http://localhost:4567/showTrees
      [
        {
            "id": "tree",
            "tree": [
                {
                    "value": 67,
                    "leftNode": 39,
                    "rightNode": 76
                },
            ...
# Unit test

El proyecto incluye unit test basicos sobre el controlador.

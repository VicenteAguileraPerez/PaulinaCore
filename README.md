# PaulinaCore
PaulinaCore is a simple way to implement firebase in proyect Java desktop using JavaFX or Java Swing

PaulinaCore library, version 1.0.0

By Vicente Aguilera Pérez

*Systems Engineering Student at Instituto Tecnológico Superior de Uruapan*

- **email:** vicente_prez@hotmail.com

About the internal structure
---------------------
**PaulinaCore library has 2 main clases.** 
- **ConnectionFirebase** class its function is connect to a Firebase project with the json getting on Firebase Console.
- **FirestoreFunctions** class its function is add, update, delete, get one and get all de documents of the database from Firestore.

**PaulinaCore library another clases** 
- **Something** interface its funcion is notify if the actions was success or return data when you add, get one or get all the information from the database
- **Options** enum has three values ADD, DELETE, UPDATE. They are necessary to indicate what was the function executed.
- **StatusActions** class has a method called statusConfirm(Options option,boolean isOk) needs two values options and isOk and if isOK 
is true then return a message
ADDING IS SUCCESS
UPDATING IS SUCCESS
DELETING IS SUCCESS

it is false return
ADDING IS NOT SUCCESS
UPDATING IS NOT SUCCESS
DELETING IS NOT SUCCESS

The parameter option is a value of the *Options* class

Version v1.0.0
---------------------
If you want to implement this library in your project, you need to create a maven proyect in your IDE:
You call this library adding these lines in the file POM.xml

     
<dependencies>

     <dependency>
	    <groupId>com.github.VicenteAguileraPerez</groupId>
	    <artifactId>PaulinaCore</artifactId>
	    <version>v1.0.0</version>
	</dependency>
    
</dependencies>

<repositories>

        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>

</repositories>

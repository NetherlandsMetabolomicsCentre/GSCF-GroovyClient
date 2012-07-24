GSCF-GroovyClient
=================

Groovy Client to interface with GSCF

> make sure Gradle 1.0 or higher is installed!

Run tests, but make sure to fill in the missing information in the constructor of the test Class!

Edit the following in GSCFTest.groovy
		
gscf.setURL('...')
gscf.setUsername('...')
gscf.setPassword('...')
gscf.setApiKey('...')

```shell
gradle test
```

Create a JAR
```shell
gradle jar
```

Build documentation
```shell
gradle groovydoc
```
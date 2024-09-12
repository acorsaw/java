## Context
This is written with the context of running Java code on an Ubuntu WSL instance.

## Background
Maven is a dependency management tool that is commonly used in Java projects. There are other tools like Gradle but this example will focus exclusively on using Maven.

For any project, having a way to consistenly install dependencies and create a repeatable
build is important to create a quality product. Maven will manage all the dependencies of a project as well as orchestrate much of the build process.

The Maven tool (`mvn`) works with the Maven Central Repository by default but it can be configured to work with other public or private repositories. To use a browser to view what is in the Maven Central Repository you can go to sites like https://mvnrepository.com/ or https://central.sonatype.com/ and look for components.

## Setup

- Ensure Java is installed and working
```bash
adam@HUAYRA:~/java/maven$ java --version
openjdk 17.0.12 2024-07-16
OpenJDK Runtime Environment (build 17.0.12+7-Ubuntu-1ubuntu222.04)
OpenJDK 64-Bit Server VM (build 17.0.12+7-Ubuntu-1ubuntu222.04, mixed mode, sharing)
```

- Ensure Maven is installed and working
```bash
adam@HUAYRA:~/java/maven$ mvn --version
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 17.0.12, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.15.153.1-microsoft-standard-wsl2", arch: "amd64", family: "unix"
```

## Initialize a new project

There are several archetypes that Maven can use to generate a project. For the
purposes of this example we are going to use the `maven-archetype-simple`. The
`maven-archetype-simple` is an artifact in Maven Central. If you go to one of 
the Maven sites you can search for it an see it's dependency information. It 
will look something like this:

```xml
<dependency>
    <groupId>org.apache.maven.archetypes</groupId>
    <artifactId>maven-archetype-simple</artifactId>
    <version>1.5</version>
</dependency>
```

If you run the command:

```bash
mvn archetype:generate \
-DgroupId=com.corsaw.example \
-DartifactId=maven-example \
-DarchetypeArtifactId=maven-archetype-simple \
-DarchetypeVersion=1.5 \
-DinteractiveMode=false
```

It will download lots of files from `repo.maven.apache.org` and if everything
goes well output a message at the end that looks like this:

```bash
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.569 s
[INFO] Finished at: 2024-09-12T04:43:48-05:00
[INFO] ------------------------------------------------------------------------
```

The newly generated project structure will look like this:

```bash
maven-example/
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── corsaw
    │               └── example
    │                   └── App.java
    ├── site
    │   └── site.xml
    └── test
        └── java
            └── com
                └── corsaw
                    └── example
                        └── AppTest.java
```

If you now go into the project and run the Maven command:

```bash
mvn clean install
```

It will yet again download many files from `repo.maven.apache.org` and show the
successful build output message:

```bash
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.113 s
[INFO] Finished at: 2024-09-12T04:49:09-05:00
[INFO] ------------------------------------------------------------------------
```

If we break down what just happened the `clean` parameter removed existing
dependencies from your project. It wasn't necessary in this situation because
we have a brand new project but it is commonly executed as a part of the
install phase. The `install` parameter looks at the `pom.xml` and does
dependency resolution. In the simple project we have there is only one
dependency:

```xml
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
    </dependency>
```

and a few default plugins for Maven like `maven-clean-plugin`, 
`maven-install-plugin`, and `maven-compiler-plugin` among others.

As a part of the `mvn clean install` you should have seen this message somewhere
in the output that shows it is downloading the artifact that you have specified
from the default location.

```bash
Downloading from central: https://repo.maven.apache.org/maven2/junit/junit/3.8.1/junit-3.8.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/junit/junit/3.8.1/junit-3.8.1.pom (998 B at 55 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/junit/junit/3.8.1/junit-3.8.1.jar
Downloaded from central: https://repo.maven.apache.org/maven2/junit/junit/3.8.1/junit-3.8.1.jar (121 kB at 4.2 MB/s)
```

Also as a part of the `mvn clean install` command it compiled all the java code,
ran unit tests, built the JAR file, and created some reports from the unit tests.
If you wanted to run some of those separately you can run the individual commands:
```bash
mvn clean
mvn compile
mvn test
mvn package
```
Separate commands like `mvn test` will still check to see if things like
`compile` need to happen and will automatically run them if necessary. For
example, if you edit a test and run `mvn test` it will automatically compile
any modified source code as well as test code prior to running the tests.
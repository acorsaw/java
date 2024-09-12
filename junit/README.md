## Testing with JUnit

JUnit is a commonly used unit test framework for Java applications. If you're
not familiar with the concepts of Unit testing you can check out
[Wikipedia](https://en.wikipedia.org/wiki/Unit_testing) or just search for
unit testing.

This example will start from the Maven example and upgrade JUnit to a version
that is current as of the writing of this README. Start by specifying the `junit-jupiter`
dependency:

```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.11.0</version>
        <scope>test</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
    </dependency>
  </dependencies>
```

Ensure that you also have plugins to run the tests:

```xml
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>3.5.0</version>
        </plugin>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-report-plugin</artifactId>
          <version>3.5.0</version>
        </plugin>
      </plugins>
    </pluginManagement>

    <plugins>
      <plugin>
        <!-- Test Execution Plugin -->
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
```

You might want to generate some HTML reports from the tests when they complete
as well:

```xml
  <reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-report-plugin</artifactId>
      </plugin>
    </plugins>
  </reporting>
```
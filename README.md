# Automated mainframe testing with jagacy

### jagacy-cucumber-bdd-example
Automated Acceptance tests for Mainframe Green screens with Jagacy &amp; Cucumber

To run the example:

```
mvn clean test

```

This will launch a test on sample interface provided with Jagacy

#### Running the tests on unix/linux boxes without any gui support 

- Install xvfb 
``` sudo apt-get install xvfb ```

- Run xvfb
```Xvfb :10 -screen 0 1920x1080x24 2>&1 >/dev/null &```

- Run the test on xvfb 
```DISPLAY=:10 mvn test```
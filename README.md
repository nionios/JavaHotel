# java-hotel

A hotel booking system implemented in Java RMI (Remote Method Invocation) that
can be used by multiple clients concurrently.

## Requirements

- OpenJDK 11.0 (in Later Versions rmic is deprecated)

## Running the Project

- Make sure to run "rmiregistry [port-number]" in the same directory as the
class files

- Then, Run either the Jar files or the Class files:

### Running the Jar files

Go into the `jar/` directory and run `java -jar JavaHotelServer.jar [options]
[suboptions]` and then `java -jar JavaHotelClient.jar [options] [suboptions]`

### Running the Class files

Go into the `bin/` directory and run `java javaHotel.server.HRServer [options]
[suboptions]` and then `java javaHotel.client.HRClient [options] [suboptions]`

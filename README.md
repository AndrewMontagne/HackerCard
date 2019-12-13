# HackerCard ![](https://travis-ci.com/AndrewMontagne/HackerCard.svg?branch=master)

Hacking around with JavaCard things!

### Requirements

 * OpenJDK 1.8
 * UNIX-like operating system (Linux, WSL, etc.)
 * Ant build system
 
### Building

To build the project, invoke `ant` in the root of the project.

Binaries will appear in `./bin` in both JavaCard 2 and 3 versions (`*.cap2` and `*.cap3` respectively)

### Acknowledgements

This project is based upon commit `468ea76` of the [AppletPlayground](https://github.com/martinpaljak/AppletPlayground) project by [Martin Paljak](https://github.com/martinpaljak), and includes the following depenencies:

 * [ant-javacard](https://github.com/martinpaljak/ant-javacard) - for building CAP files (MIT)
 * [GlobalPlatformPro](https://github.com/martinpaljak/GlobalPlatformPro) - for loading the applets to the card (LGPL3)
 * JavaCard SDK 2.2.2 and 3.0.3 ([Oracle Licence Agreement](https://www.oracle.com/a/tech/docs/otn-java-card-classic-connected-sdk-4april2012.html))
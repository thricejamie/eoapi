name := "eoapi-lift"

version := "0.1"

scalaVersion := "2.9.0-1"

seq(WebPlugin.webSettings: _*)

libraryDependencies ++= {
  val liftVersion = "2.4-M3"
  Seq(
    "net.databinder" %% "dispatch-http" % "0.8.4",
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile",
    "org.mortbay.jetty" % "jetty" % "6.1.26" % "jetty",
    "junit" % "junit" % "4.7" % "test",
    "ch.qos.logback" % "logback-classic" % "0.9.26",
    "org.scala-tools.testing" %% "specs" % "1.6.8" % "test",
    "com.h2database" % "h2" % "1.2.147",
    "postgresql" % "postgresql" % "8.4-701.jdbc4"
  )
}

resolvers += Resolver.url("Java.net Repo", url("http://download.java.net/maven/2/"))

scalacOptions ++= Seq("-unchecked", "-deprecation")
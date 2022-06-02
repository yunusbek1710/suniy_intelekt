import Dependencies._

lazy val projectSettings = Seq(version := "1.0", scalaVersion := "2.13.8")

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("modules/common"))
  .settings(
    libraryDependencies ++= circeLibs ++ catsLibs ++ cirisLibs
  )
  .settings(projectSettings: _*)

lazy val server = (project in file("modules/server"))
  .settings(projectSettings: _*)
  .settings(
    name := "Mahalla",
    libraryDependencies ++= coreLibraries
  )
  .settings(
    scalaJSProjects         := Seq(client),
    Assets / pipelineStages := Seq(scalaJSPipeline),
    pipelineStages          := Seq(digest, gzip),
    Compile / compile       := ((Compile / compile) dependsOn scalaJSPipeline).value)
  .enablePlugins(WebScalaJSBundlerPlugin)

lazy val tests = project
  .in(file("modules/tests"))
  .settings(projectSettings: _*)
  .configs(IntegrationTest)
  .settings(
    name := "Mahalla-test-suite",
    Defaults.itSettings,
    libraryDependencies ++= testLibraries ++ testLibraries.map(_ % Test)
  )
  .dependsOn(server)

lazy val client = (project in file("modules/client"))
  .settings(projectSettings: _*)
  .settings(
    name := "client",
    scalaJSUseMainModuleInitializer := true,
    resolvers += Resolver.sonatypeRepo("releases"),
    libraryDependencies ++= Seq(
      "io.github.chronoscala"             %%% "chronoscala"   % "2.0.2",
      "com.github.japgolly.scalajs-react" %%% "core"          % Versions.scalaJsReact,
      "com.github.japgolly.scalajs-react" %%% "extra"         % Versions.scalaJsReact,
      "com.github.japgolly.scalacss"      %%% "ext-react"     % Versions.scalaCss,
      "io.circe"                          %%% "circe-core"    % Versions.circe,
      "io.circe"                          %%% "circe-parser"  % Versions.circe,
      "io.circe"                          %%% "circe-generic" % Versions.circe,
      "io.circe"                          %%% "circe-refined" % Versions.circe,
      "eu.timepit"                        %%% "refined"       % Versions.refined,
      "io.udash"                          %%% "udash-jquery"  % "3.2.0"
    ),
    webpackEmitSourceMaps := false,
    Compile / npmDependencies ++= Seq(
      "react" -> Versions.reactJs,
      "react-dom" -> Versions.reactJs
    )
  )
  .enablePlugins(ScalaJSBundlerPlugin)
  .dependsOn(common.js)


lazy val mahalla = (project in file("."))
  .aggregate(server, client, tests)

Global / onLoad := (Global / onLoad).value.andThen(state => "project server" :: state)

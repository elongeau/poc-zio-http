// give the user a nice default project!
ThisBuild / organization := "com.github.elongeau"
ThisBuild / version      := "1.0.0"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(BuildHelper.stdSettings)
  .settings(
    name := "poc-zio-http",
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= Dependencies.dependencies,
  )
  .settings(
    Docker / version          := version.value,
    Compile / run / mainClass := Option("com.github.elongeau.pocziohttp.Pocziohttp"),
  )

addCommandAlias("fmt", "scalafmt; Test / scalafmt; sFix;")
addCommandAlias("fmtCheck", "scalafmtCheck; Test / scalafmtCheck; sFixCheck")
addCommandAlias("sFix", "scalafix OrganizeImports; Test / scalafix OrganizeImports")
addCommandAlias(
  "sFixCheck",
  "scalafix --check OrganizeImports; Test / scalafix --check OrganizeImports",
)

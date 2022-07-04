import sbt._

object Versions     {
  val ZioVersion   = "1.0.14"
  val ZHTTPVersion = "1.0.0.0-RC24"
  val ZioJson      = "0.3.0-RC8"
}
object Dependencies {
  val dependencies = Seq(
    "dev.zio" %% "zio-test"     % Versions.ZioVersion   % Test,
    "dev.zio" %% "zio-test-sbt" % Versions.ZioVersion   % Test,
    "io.d11"  %% "zhttp"        % Versions.ZHTTPVersion,
    "io.d11"  %% "zhttp"        % Versions.ZHTTPVersion % Test,
    "dev.zio" %% "zio-json"     % Versions.ZioJson,
  )
}

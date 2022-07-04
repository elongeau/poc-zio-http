package com.github.elongeau.pocziohttp

import zhttp.http._
import zhttp.service.server.ServerChannelFactory
import zhttp.service.{EventLoopGroup, Server}
import zio.json._

object Main extends App {
  val app: Http[Has[Greeting.Service], Throwable, Request, Response] = Http.collectZIO[Request] {
    case Method.GET -> !! / "text" =>
      for {
        greetings <- Greeting.greet
      } yield Response.text(greetings.toJson)
  }

  private val server =
    Server.port(8090) ++ // Setup port
      Server.app(app)    // Setup the Http app

  // Run it like any simple app
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val value: ZIO[zio.ZEnv, Any, Nothing] = server
      .make
      .use(start =>
        console.putStrLn(s"Server started on port ${start.port}")
          *> ZIO.never,
      )
      .provideCustomLayer(ServerChannelFactory.auto ++ EventLoopGroup.auto(2) ++ Greeting.live)

    value.exitCode
  }
}

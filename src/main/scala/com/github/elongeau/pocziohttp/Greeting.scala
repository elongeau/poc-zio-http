package com.github.elongeau.pocziohttp

import zio._
import zio.json.JsonEncoder

object Greeting {
  trait Service {
    def greet: Task[Greet]
  }

  case class Greet(s: String)
  object Greet {
    implicit val encoder: JsonEncoder[Greet] = ???
  }

  val greet: ZIO[Has[Greeting.Service], Throwable, Greet] = ZIO.accessM { service =>
    service.get.greet
  }

  val greets: Service = new Service {
    override def greet: Task[Greet] = Task(Greet("hello world"))
  }

  val live: ULayer[Has[Greeting.Service]] = ZLayer.succeed(greets)
}

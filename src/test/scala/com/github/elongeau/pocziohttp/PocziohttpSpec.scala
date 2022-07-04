package com.github.elongeau.pocziohttp

import zio.test._
import zio.test.Assertion._
import zhttp.http._

object PocziohttpSpec extends DefaultRunnableSpec {
  override def spec: ZSpec[Environment, Failure] = suite("""PocziohttpSpec""")(
    testM("200 ok") {
      checkAllM(Gen.fromIterable(List("text"))) { uri =>
        val request = Request(Method.GET, URL(!! / uri))
        assertM(Main.app(request).map(_.status))(equalTo(Status.OK))
      }
    },
  ).provideCustomLayer(Greeting.live)
}

package com.github.kzmake.sample.akka

import akka.actor.{ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout
import spray.routing.SimpleRoutingApp

import scala.concurrent.duration._
import scala.language.postfixOps

object Main extends App with SimpleRoutingApp {

  implicit val system: ActorSystem = ActorSystem()
  implicit val timeout: Timeout = Timeout(20.seconds)

  import system.dispatcher

  val request = system.actorOf(Props(classOf[RequestRouter]), "request")


  startServer(interface = "localhost", port = 4000) {
    pathPrefix("delay") {
      pathPrefix("[1-9]+[0-9]*".r) { milliseconds =>
        pathEnd {
          complete {
            val result = request ? DelayService.Delay(milliseconds.toInt)

            result.mapTo[String]
          }
        }
      } ~ pathPrefix("random") {
        pathEnd {
          complete {
            val result = request ? DelayService.Random

            result.mapTo[String]
          }
        }
      }
    }
  }
}

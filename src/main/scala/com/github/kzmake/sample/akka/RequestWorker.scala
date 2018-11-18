package com.github.kzmake.sample.akka

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

class RequestWorker extends Actor {
  val log = Logging(context.system, this)

  implicit val timeout: Timeout = Timeout(20.seconds)
  val delayService: ActorRef = context.actorOf(Props(classOf[DelayService]), "delay_service")


  override def receive: Receive = {
    case DelayService.Delay(milliseconds) =>
      log.info("Begin ~ delay: " + milliseconds)

      val result = Await.result(delayService ? DelayService.Delay(milliseconds), timeout.duration)

      log.info("End ~ delay: " + milliseconds)

      sender ! result.toString

    case DelayService.Random =>
      log.info("Begin ~ delay: random")

      val result = Await.result(delayService ? DelayService.Random, timeout.duration)

      log.info("End ~ delay: random")

      sender ! result.toString
  }
}
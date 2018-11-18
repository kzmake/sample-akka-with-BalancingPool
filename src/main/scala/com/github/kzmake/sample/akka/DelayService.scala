package com.github.kzmake.sample.akka

import akka.actor.Actor
import akka.event.Logging

import scala.util.Random

object DelayService {

  case class Delay(milliseconds: Int)

  case object Random

}

class DelayService extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case DelayService.Delay(milliseconds) =>
      delay(milliseconds)

      sender ! ""

    case DelayService.Random =>
      val random = Random.nextInt(10000)

      delay(random)

      sender ! ""
  }

  def delay(milliseconds: Int) = {
    log.info("Thread.sleep: " + milliseconds)
    Thread.sleep(milliseconds)
  }
}
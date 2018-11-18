package com.github.kzmake.sample.akka

import akka.actor.{Actor, ActorRef, Props}
import akka.routing.BalancingPool

class RequestRouter extends Actor {
  val nWorkers = 2
  val workerRouterPool: ActorRef =
    context.actorOf(Props[RequestWorker].withRouter(BalancingPool(nWorkers)), name = "request_router")

  def receive = {
    case x =>
      println("Forward |> message: " + x)
      workerRouterPool.forward(x)
  }
}

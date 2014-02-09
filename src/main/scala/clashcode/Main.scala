package clashcode

import akka.actor.{Props, ActorSystem}
import akka.routing.BroadcastRouter
import akka.cluster.routing.{ClusterRouterSettings, ClusterRouterConfig}
import com.typesafe.config.ConfigFactory

object Main extends App {

  override def main(args: Array[String]) {

    // my actor system
    val system = ActorSystem("cluster")

    // quit when return key was pressed
    readLine()
    system.shutdown()
  }

}
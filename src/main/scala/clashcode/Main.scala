package clashcode

import akka.actor.{Props, ActorSystem}
import akka.routing.BroadcastRouter
import akka.cluster.routing.{ClusterRouterSettings, ClusterRouterConfig}
import com.typesafe.config.ConfigFactory

object Main extends App {

  override def main(args: Array[String]) {

    // allow configuration of ports via command line argument
    val maybePortConfig = args.headOption.map(port => ConfigFactory.parseString("akka.remote.netty.tcp.port = " + port))
    val config = maybePortConfig.map(_.withFallback(ConfigFactory.load)).getOrElse(ConfigFactory.load)

    // my actor system
    val system = ActorSystem("cluster", config)

    // this router sends messages to up to 100 other "main" actors in the cluster
    val broadcastRouter = system.actorOf(Props.empty.withRouter(
      ClusterRouterConfig(
        BroadcastRouter(),
        ClusterRouterSettings(totalInstances = 100, routeesPath = "/user/main", allowLocalRoutees = true, useRole = None))),
      name = "router")

      
    {
      val strat = SampleStrategy("01")
      val sampleActor = system.actorOf(Props(classOf[SampleActor], broadcastRouter, strat), strat.name)
      sampleActor ! Evolve
    }
    {
      val strat = SampleStrategy("02")
      val sampleActor = system.actorOf(Props(classOf[SampleActor], broadcastRouter, strat), strat.name)
      sampleActor ! Evolve
    }
    {
      val strat = MutatingStrategy("01", 10)
      val sampleActor = system.actorOf(Props(classOf[SampleActor], broadcastRouter, strat), strat.name)
      sampleActor ! Evolve
    }
    {
      val strat = MutatingStrategy("02", 50)
      val sampleActor = system.actorOf(Props(classOf[SampleActor], broadcastRouter, strat), strat.name)
      sampleActor ! Evolve
    }
    {
      val strat = MutatingStrategy("03", 100)
      val sampleActor = system.actorOf(Props(classOf[SampleActor], broadcastRouter, strat), strat.name)
      sampleActor ! Evolve
    }

    readLine()
    system.shutdown()
  }

}
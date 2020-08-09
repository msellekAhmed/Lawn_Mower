package lawnmower.core.main

import scala.Console.err
import scala.sys.exit

object App {

  def main(args : Array[String]) = {

    ArgumentParser.parseArguments(args) match {
      case Some(arguments) => launch(arguments.file).recover { case e: Throwable => err.println(e.getMessage); exit(2)}
      case None => exit(1)
    }

  }

}

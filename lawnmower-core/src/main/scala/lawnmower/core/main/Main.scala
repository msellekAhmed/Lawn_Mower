package lawnmower.core.main

import lawnmower.core.launcher.ApplicationLauncher
import lawnmower.core.parser.ArgumentParser

import scala.Console.err
import scala.sys.exit

object Main extends App {


    ArgumentParser.parseArguments(args) match {
      case Some(arguments) => ApplicationLauncher.launch(arguments.file).recover { case e: Throwable => err.println(e.getMessage); exit(2)}
      case None => exit(1)
    }

}

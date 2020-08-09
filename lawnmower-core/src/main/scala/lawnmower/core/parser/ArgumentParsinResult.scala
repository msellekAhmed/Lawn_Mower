package lawnmower.core.parser

import java.io.File

/**
  * Case class representing the arguments parsed from the command line. Default parameters values are only used when
  * the argument is defined as optional (see [[]]).
  * @param file the file from which lawn-mowers should be parsed.
  */
case class ArgumentParsinResult(file: File = new File(".")) {}

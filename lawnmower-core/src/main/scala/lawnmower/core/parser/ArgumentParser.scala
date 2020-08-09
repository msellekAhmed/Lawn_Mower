import java.io.File

import lawnmower.core.parser.ArgumentParsinResult
import scopt.OptionParser

/**
  * Parser of command-line's arguments filling its result inside a [[]] instance and
  * displaying an usage output if the input is invalid.
  * @see [[OptionParser]]
  */
object ArgumentParser extends OptionParser[ArgumentParsinResult]("lawn-mower") {

  arg[File]("<file>") text "source file path" action { (argument, parsingResult) =>
    parsingResult.copy(file = argument)
  } validate { argument =>
    if (argument.isFile) success else failure("Argument <file> must be a valid file")
  }

  /**
    * Parse the given arguments filling the result inside a [[ArgumentParsinResult]]. Parsing is delegating to the
    * [[OptionParser.parse( )]] method.
 *
    * @param args the arguments to be parsed.
    * @return the parsing result as an [[Option]] of [[ArgumentParsinResult]].
    */
  def parseArguments(args: Seq[String]) = parse(args, ArgumentParsinResult())
}

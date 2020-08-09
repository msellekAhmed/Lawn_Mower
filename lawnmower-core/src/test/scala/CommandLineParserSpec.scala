import java.io.File

/**
 * Test cases about [[ArgumentParser]].
 * @see [[BaseSpec]].
 */
@UnitTest
class CommandLineParserSpec extends BaseSpec {

  "A command-line parser" should "fail parsing invalid arguments" in {
    val existingFilePath = getClass.getResource("/existingFile").getPath
    val invalidArguments = Table("arguments", Seq(), Seq("-f"), Seq("-o", existingFilePath), Seq("missingFile"))

    forAll(invalidArguments) { (arguments) =>
      // Act
      val result = CommandLineParser.parseArguments(arguments)

      // Assert
      result.isEmpty should equal(true)
    }
  }

  it should "success parsing valid arguments" in {
    // Arrange
    val existingFilePath = getClass.getResource("/existingFile").getPath
    val arguments = Seq(existingFilePath)

    // Act
    val result = CommandLineParser.parseArguments(arguments)

    // Assert
    result.value.file should equal(new File(existingFilePath))
  }
}

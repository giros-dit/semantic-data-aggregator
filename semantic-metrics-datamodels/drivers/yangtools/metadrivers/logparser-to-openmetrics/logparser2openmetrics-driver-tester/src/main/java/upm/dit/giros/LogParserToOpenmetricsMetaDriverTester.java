package upm.dit.giros;

/**
 * LogParser to Openmetrics YANGTools Meta Driver Tester.
 */
public class LogParserToOpenmetricsMetaDriverTester 
{
    public static void main( String[] args ) {
        LogParserSourceDriverTester logparser_source = new LogParserSourceDriverTester();
        LogParserToOpenmetricsTransformerDriverTester logparser_transformer = new LogParserToOpenmetricsTransformerDriverTester("","","","");
        logparser_source.start();
        logparser_transformer.start();
    }
}

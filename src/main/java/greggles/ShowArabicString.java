/*
package greggles;

import javax.swing.*;

// This example Swing code was adapted from this web page:
// http://www.javapractices.com/topic/TopicAction.do?Id=231
public class ShowArabicString
{
    private static final String ARABIC_TEXT = "\u0C24\u0C46\u0C32\u0C41\u0C17\u0C41 \u0C05\u0C28\u0C47\u0C26\u0C3F \u0C26\u0C4D\u0C30\u0C3E\u0C35\u0C3F\u0C21 \u0C2D\u0C3E\u0C37\u0C32 \u0C15\u0C41\u0C1F\u0C41\u0C02\u0C2C\u0C3E\u0C28\u0C3F\u0C15\u0C3F \u0C1A\u0C46\u0C02\u0C26\u0C3F\u0C28 \u0C2D\u0C3E\u0C37.";

    public static void main ( String... aArgs )
    {

        System.out.println( "java.vendor : " + System.getProperties().getProperty( "java.vendor" ) );
        System.out.println( "Runtime.version : " + Runtime.version() );
        System.out.println( "ARABIC_TEXT = " + ARABIC_TEXT );

        ShowArabicString app = new ShowArabicString();
        app.buildAndDisplayGui();
    }

    // PRIVATE

    private void buildAndDisplayGui ( )
    {
        JFrame frame = new JFrame( "Show Arabic text" );
        buildContent( frame );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible( true );
    }

    private void buildContent ( JFrame aFrame )
    {
        JPanel panel = new JPanel();
        panel.add( new JLabel( ARABIC_TEXT ) );
        aFrame.getContentPane().add( panel );
    }
}
*/
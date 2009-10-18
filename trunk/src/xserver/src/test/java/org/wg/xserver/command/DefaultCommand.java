package org.wg.xserver.command;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.wg.xserver.command.Command;
import org.wg.xserver.command.CommandMessage;
import org.wg.xserver.context.Context;

/**
 * @author enychen Oct 12, 2009
 */
public class DefaultCommand implements Command {

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.command.Command#execute(org.wg.xserver.command.CommandMessage,
     *      org.wg.xserver.Context)
     */
    public void execute(CommandMessage commandMessage, Context context) {
        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = null;

        try {
            charBuffer = decoder.decode(commandMessage.getMessage());
        } catch (CharacterCodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String result = charBuffer.toString();

        System.out.println(result);
    }

}

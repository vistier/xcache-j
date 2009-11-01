package org.wg.xio.command;

import org.wg.xio.context.Context;
import org.wg.xio.ex.command.Command;
import org.wg.xio.ex.command.CommandRequest;

/**
 * 测试命令
 * @author enychen Oct 12, 2009
 */
public class TestCommand implements Command {

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.Command#execute(org.wg.xio.ex.command.CommandRequest,
     *      org.wg.xio.Context)
     */
    public void execute(CommandRequest commandRequest, Context context) {
        TestRequest testRequest = new TestRequest(commandRequest);
        testRequest.decode(commandRequest.getMessage());

        String out = context.getHostAddress() + "-测试命令，length=" + testRequest.getLength() + ", id="
                + testRequest.getId() + ", commandId=" + testRequest.getCommandId() + ", test="
                + testRequest.getTest();
        // System.out.println(out);

        // byte[] bodyBytes = out.getBytes();
        // ByteBuffer body = ByteBuffer.allocate(bodyBytes.length);
        // body.put(bodyBytes);
        // body.flip();
        //        
        // LengthMessage lengthMessage = new LengthMessage();
        // lengthMessage.setBody(body);
        // context.write(lengthMessage);
        
        TestResponse testResponse = new TestResponse();
        testResponse.setId(testRequest.getId());
        testResponse.setTest(out);
        context.write(testResponse);
    }
}

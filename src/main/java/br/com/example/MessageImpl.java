package br.com.example;

import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class MessageImpl extends MessageGrpc.MessageImplBase{

    Logger logger = Logger.getLogger(MessageImpl.class.getName());

    @Override
    public void messageService(MessageOuterClass.MessageRequest request, StreamObserver<MessageOuterClass.MessageResponse> response) {

        // Generate a greeting message for the original method
        try {
            ClasseMeta.start("teste");
        }catch(Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        MessageOuterClass.MessageResponse messageResponse = MessageOuterClass.MessageResponse.newBuilder().setMessage("Hello " + request.getName()).build();

        // Send the reply back to the client.
        response.onNext(messageResponse);

        // Indicate that no further messages will be sent to the client.
        response.onCompleted();


    }

}

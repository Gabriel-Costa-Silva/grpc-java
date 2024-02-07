package br.com.example;

import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class MessageImpl extends MessageGrpc.MessageImplBase{

    Logger logger = Logger.getLogger(MessageImpl.class.getName());

    @Override
    public void messageService(MessageOuterClass.MessageRequest request, StreamObserver<MessageOuterClass.MessageResponse> response) {
        MessageOuterClass.MessageResponse messageResponse = null;
        // Generate a greeting message for the original method
        try {
             messageResponse = MessageOuterClass.MessageResponse.newBuilder().setMessage(ClasseMeta.start(request.getName())).build();
        }catch(Exception e){
            e.printStackTrace();
        }

        // Send the reply back to the client.
        response.onNext(messageResponse);

        // Indicate that no further messages will be sent to the client.
        response.onCompleted();


    }

}

package br.com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class MessageServer {
    public static void main(String [] args) {
        Server server  = ServerBuilder
                .forPort(9090)
                .addService(new MessageImpl()).build();
    try{
        server.start();
        server.awaitTermination();
    }catch(IOException|InterruptedException e ){
        Logger.getLogger("ERROS:"+e.getMessage());

    }



    }




}

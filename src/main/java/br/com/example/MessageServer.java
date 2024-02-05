package br.com.example;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;


import java.io.IOException;
import java.util.logging.Logger;

public class MessageServer {
    public static void main(String [] args) {

        BindableService prs = ProtoReflectionService.newInstance();
  //      prs.bindService();
        Server server  = ServerBuilder
                .forPort(9090)
                .addService(prs)
                .addService(new MessageImpl()).build();
    try{
        server.start();
        server.awaitTermination();
    }catch(IOException|InterruptedException e ){
        Logger.getLogger("ERROS:"+e.getMessage());

    }



    }




}

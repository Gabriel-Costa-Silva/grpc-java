package br.com.example;

import com.google.protobuf.Message;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.apache.hop.core.HopEnvironment;
import org.apache.hop.core.exception.HopException;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class MessageServer {
    private static Properties properties;
    public static void main(String [] args) {
        Logger logger = Logger.getLogger(MessageServer.class.getName());
        properties = new Properties();

        try (InputStream input = MessageServer.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);

            //instanciamento de propriedades
            System.setProperty("HOP_PLUGIN_BASE_FOLDER",properties.getProperty("hop.plugin"));
//            System.setProperty("HOP_PLUGIN_BASE_FOLDER",prop.getProperty("hop.plugin"));
//            System.setProperty("HOP_PLUGIN_BASE_FOLDER",prop.getProperty("hop.plugin"));

            HopEnvironment.init();



            BindableService prs = ProtoReflectionService.newInstance();
            Server server  = ServerBuilder
                .forPort(9090)
                .addService(prs)
                .addService(new MessageImpl()).build();
            server.start();
            server.awaitTermination();
        }catch(IOException|InterruptedException |HopException e ) {
            logger.severe("ERROS:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static Properties getProperties(){
        return properties;
    }

}

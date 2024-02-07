package br.com.example;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;


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
            System.setProperty("HOP_PLUGIN_BASE_FOLDERS",properties.getProperty("hop.plugin.folder"));
            System.setProperty("HOP_CONFIG_FOLDER",properties.getProperty("hop.config.folder"));
            System.setProperty("HOP_DISABLE_CONSOLE_LOGGING","N"); //habilita/desabilita log da execução do pipe


            BindableService prs = ProtoReflectionService.newInstance();
            Server server  = ServerBuilder
                .forPort(9090)
                .addService(prs)
                .addService(new MessageImpl()).build();
            server.start();
            logger.info("Servidor iniciado");

            server.awaitTermination();
        }catch(IOException|InterruptedException e ) {
            logger.severe("ERROS:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static Properties getProperties(){
        return properties;
    }

}

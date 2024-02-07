package br.com.example;


import org.apache.hop.core.HopEnvironment;
import org.apache.hop.core.Result;
import org.apache.hop.core.RowMetaAndData;
import org.apache.hop.core.exception.HopException;
import org.apache.hop.core.logging.*;
import org.apache.hop.core.row.value.ValueMetaString;
import org.apache.hop.core.variables.IVariables;
import org.apache.hop.core.variables.Variables;
import org.apache.hop.metadata.serializer.json.JsonMetadataProvider;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.RowProducer;

import org.apache.hop.pipeline.engines.local.LocalPipelineEngine;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class ClasseMeta {
    private static Logger logger = Logger.getLogger(ClasseMeta.class.getName());
    private final static String STEP_NAME_INJECTOR = "Injector";
    private final static String FIELD_NAME_MSG_ENTRADA = "MsgEntrada";


    //HopEnvironment.init();

    private static void initHopEnvironment() throws HopException{
        logger.info("Parametros do sistema!");
        logger.info(System.getProperty("HOP_CONFIG_FOLDER"));
        logger.info(System.getProperty("HOP_PLUGIN_BASE_FOLDERS"));
        logger.info("Arquivo de pipeline a ser executado");
        logger.info(MessageServer.getProperties().getProperty("hop.pipeline.file"));
        if(!HopEnvironment.isInitialized()){
            logger.info("Iniciando HopEnvironment !");

            HopEnvironment.init();
            logger.info("HopEnvironment iniciado!");
        }else{
            logger.info("HopEnvironment já inicializado!");
        }
    }
    public static String start (String conteudoMensagem) throws Exception {

        String returnString = "";

        initHopEnvironment();
        IVariables ivariables = Variables.getADefaultVariableSpace();

        JsonMetadataProvider jsonMetadataProvider = new JsonMetadataProvider();
        jsonMetadataProvider.setBaseFolder(MessageServer.getProperties().getProperty("hop.metadata.folder").toString());

        //utilizando arquivo diretamente dos recursos do projeto
        PipelineMeta pipelineMeta = new PipelineMeta(
                MessageServer.getProperties().getProperty("hop.pipeline.file"),
                jsonMetadataProvider,
                ivariables);


        SimpleLoggingObject simpleLoggingObject = new SimpleLoggingObject("upper-case", LoggingObjectType.PIPELINE,null);

        LocalPipelineEngine localPipelineEngine = new LocalPipelineEngine(pipelineMeta,ivariables,null);//investigar este parent -> iLoggingObject
        //LocalPipelineEngine localPipelineEngine = (LocalPipelineEngine) PipelineEngineFactory.createPipelineEngine(ivariables,"local",jsonMetadataProvider,pipelineMeta);
        localPipelineEngine.setLogLevel(LogLevel.BASIC);

        localPipelineEngine.prepareExecution();


        logger.info("Entrada do processamento do pipeline");

        RowProducer rowProducer = localPipelineEngine.addRowProducer(STEP_NAME_INJECTOR,0); //??valor //localiza o Injector dentro do arquivo .hpl

        RowMetaAndData rowMetaAndData = new RowMetaAndData(); //String e conteudo
        rowMetaAndData.addValue(new ValueMetaString(FIELD_NAME_MSG_ENTRADA),conteudoMensagem); //campo que será injetado
        rowProducer.putRow(rowMetaAndData.getRowMeta(),rowMetaAndData.getData());

        rowProducer.finished();

        localPipelineEngine.startThreads();

        localPipelineEngine.waitUntilFinished();

        Result result = localPipelineEngine.getResult();
        AtomicReference<String> retorno = new AtomicReference<>("");
        result.getRows().forEach(rmd->{
            for(int i = 0;i <rmd.size();i++){
               if(rmd.getRowMeta().getFieldNames()[i].equals("MsgSaida")){
                   retorno.set(rmd.getData()[i].toString());
               }

            }
        } );
        logger.info("Retorno do KTR "+ retorno);
        LoggingBuffer loggingBuffer = HopLogStore.getAppender();
        String log = loggingBuffer.getBuffer(localPipelineEngine.getLogChannelId(),false).toString();

        return retorno.get();
    }


}

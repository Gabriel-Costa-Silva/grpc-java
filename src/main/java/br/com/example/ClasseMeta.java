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
        if(!HopEnvironment.isInitialized()){
            logger.info("Iniciando HopEnvironment !");
            HopEnvironment.init();
            logger.info(System.getProperty("HOP_CONFIG_FOLDER"));
            logger.info(System.getProperty("HOP_PLUGIN_BASE_FOLDERS"));
            logger.info("HopEnvironment iniciado!");
        }else{
            logger.info("HopEnvironment já inicializado!");
        }

    }
    public static void start (String conteudoMensagem) throws Exception {

        initHopEnvironment();
        IVariables ivariables = Variables.getADefaultVariableSpace();

        JsonMetadataProvider jsonMetadataProvider = new JsonMetadataProvider();
        //jsonMetadataProvider.setBaseFolder(MessageServer.getProperties().getProperty("hop.metadata.folder").toString());
        jsonMetadataProvider.setBaseFolder("D:/apache-hop-client-2.7.0/hop/home/Metadata");


//        PipelineMeta pipelineMeta = new PipelineMeta(
//                ClasseMeta.class.getClassLoader().getResourceAsStream("upper-case.hpl")
//                ,jsonMetadataProvider,
//                //true,// tem na documentaçaõ, mas n tem nessa versão do apache-hop
//                ivariables);

        PipelineMeta pipelineMeta = new PipelineMeta(
                "D:\\apache-hop-client-2.7.0\\hop\\home\\x.hpl",
                jsonMetadataProvider,
                //true,// tem na documentaçaõ, mas n tem nessa versão do apache-hop
                ivariables);

//        retirado de https://blog.csdn.net/qq_41482600/article/details/129751239
//        IPipelineEngine pipelineEngine = PipelineEngineFactory.createPipelineEngine(
//                ivariables,
//                "local",
//                jsonMetadataProvider,
//                pm
//        );
        SimpleLoggingObject simpleLoggingObject = new SimpleLoggingObject("upper-case", LoggingObjectType.PIPELINE,null);

        LocalPipelineEngine localPipelineEngine = new LocalPipelineEngine(pipelineMeta,ivariables,null);//investigar este parent -> iLoggingObject
        //LocalPipelineEngine localPipelineEngine = (LocalPipelineEngine) PipelineEngineFactory.createPipelineEngine(ivariables,"local",jsonMetadataProvider,pipelineMeta);
        localPipelineEngine.setLogLevel(LogLevel.BASIC);

        localPipelineEngine.prepareExecution();


        logger.info("Entrada do processamento do pipeline");
        logger.info("metodo 1");

        RowProducer rowProducer = localPipelineEngine.addRowProducer(STEP_NAME_INJECTOR,0); //??valor //localiza o Injector dentro do arquivo .hpl
        logger.info("metodo 2");

        RowMetaAndData rowMetaAndData = new RowMetaAndData(); //String e conteudo
        rowMetaAndData.addValue(new ValueMetaString(FIELD_NAME_MSG_ENTRADA),conteudoMensagem); //campo que será injetado
        //pegar o row e
        rowProducer.putRow(rowMetaAndData.getRowMeta(),rowMetaAndData.getData());
        logger.info("metodo 3");

        rowProducer.finished();
        logger.info("metodo 4");

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
    }


}

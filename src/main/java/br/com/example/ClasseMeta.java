package br.com.example;

import com.google.api.client.util.Value;
import org.apache.hop.core.HopEnvironment;
import org.apache.hop.core.Result;
import org.apache.hop.core.RowMetaAndData;
import org.apache.hop.core.exception.HopException;
import org.apache.hop.core.logging.HopLogStore;
import org.apache.hop.core.logging.LogLevel;
import org.apache.hop.core.logging.LoggingBuffer;
import org.apache.hop.core.row.value.ValueMetaString;
import org.apache.hop.core.variables.IVariables;
import org.apache.hop.core.variables.Variables;
import org.apache.hop.metadata.serializer.json.JsonMetadataProvider;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.RowProducer;
import org.apache.hop.pipeline.engine.IPipelineEngine;
import org.apache.hop.pipeline.engine.PipelineEngineFactory;
import org.apache.hop.pipeline.engines.local.LocalPipelineEngine;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

public class ClasseMeta {

    //HopEnvironment.init();
    public void start (String conteudoMensagem) throws Exception {

        LocalPipelineEngine localPipelineEngine = getLocalPipelineEngine();

        RowProducer rowProducer = localPipelineEngine.addRowProducer("Injector",0); //localiza o Injector dentro do arquivo .hpl

        RowMetaAndData rowMetaAndData = new RowMetaAndData(); //String e conteudo
        rowMetaAndData.addValue(new ValueMetaString("MsgEntrada"),"Conteudo entrada"); //campo que será injetado
        //pegar o row e
        rowProducer.putRow(rowMetaAndData.getRowMeta(),rowMetaAndData.getData());

        rowProducer.finished();

        localPipelineEngine.startThreads(); //throw
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
        LoggingBuffer loggingBuffer = HopLogStore.getAppender();
        String log = loggingBuffer.getBuffer(localPipelineEngine.getLogChannelId(),false).toString();
    }

    @NotNull
    private static LocalPipelineEngine getLocalPipelineEngine() throws HopException {
        IVariables ivariables = Variables.getADefaultVariableSpace();
        //caso venha a usar
        //variables.setVariable("teste","teste");
       // String metadataFolder = MessageServer.getProperties().getProperty("hop.metadata.folder").toString();

        JsonMetadataProvider jsonMetadataProvider = new JsonMetadataProvider();
        jsonMetadataProvider.setBaseFolder(MessageServer.getProperties().getProperty("hop.metadata.folder").toString());

        PipelineMeta pm = new PipelineMeta(
                ClasseMeta.class.getClassLoader().getResourceAsStream("upper-case.hpl")
                ,jsonMetadataProvider,
                ivariables);
//        retirado de https://blog.csdn.net/qq_41482600/article/details/129751239
//        IPipelineEngine pipelineEngine = PipelineEngineFactory.createPipelineEngine(
//                ivariables,
//                "local",
//                jsonMetadataProvider,
//                pm
//        );
        LocalPipelineEngine localPipelineEngine = new LocalPipelineEngine(pm,ivariables,null);//investigar este parent

        localPipelineEngine.setLogLevel(LogLevel.BASIC);
        localPipelineEngine.prepareExecution();
        return localPipelineEngine;
    }

}

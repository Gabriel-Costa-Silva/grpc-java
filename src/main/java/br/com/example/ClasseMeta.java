package br.com.example;

import org.apache.hop.core.Result;
import org.apache.hop.core.RowMetaAndData;
import org.apache.hop.core.logging.HopLogStore;
import org.apache.hop.core.logging.LogLevel;
import org.apache.hop.core.logging.LoggingBuffer;
import org.apache.hop.core.row.value.ValueMetaString;
import org.apache.hop.core.variables.IVariables;
import org.apache.hop.core.variables.Variables;
import org.apache.hop.metadata.serializer.json.JsonMetadataProvider;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.RowProducer;
import org.apache.hop.pipeline.engines.local.LocalPipelineEngine;

import java.util.concurrent.atomic.AtomicReference;

public class ClasseMeta {

    //HopEnvironment.init();
    public void start () throws Exception {
        IVariables ivariables = Variables.getADefaultVariableSpace();

        JsonMetadataProvider jsonMetadataProvider = new JsonMetadataProvider();
        jsonMetadataProvider.setBaseFolder("caminho do /metadata");

        PipelineMeta pm = new PipelineMeta("caminho do arquivo.hpl",jsonMetadataProvider,ivariables);

        LocalPipelineEngine localPipelineEngine = new LocalPipelineEngine(pm,ivariables,null);//investigar este parent

        localPipelineEngine.setLogLevel(LogLevel.BASIC);
        localPipelineEngine.prepareExecution();


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

}

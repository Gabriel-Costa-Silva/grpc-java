package br.com.example;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Message.proto")
public final class MessageGrpc {

  private MessageGrpc() {}

  public static final String SERVICE_NAME = "Message";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<br.com.example.MessageOuterClass.MessageRequest,
      br.com.example.MessageOuterClass.MessageResponse> getMessageServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MessageService",
      requestType = br.com.example.MessageOuterClass.MessageRequest.class,
      responseType = br.com.example.MessageOuterClass.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.com.example.MessageOuterClass.MessageRequest,
      br.com.example.MessageOuterClass.MessageResponse> getMessageServiceMethod() {
    io.grpc.MethodDescriptor<br.com.example.MessageOuterClass.MessageRequest, br.com.example.MessageOuterClass.MessageResponse> getMessageServiceMethod;
    if ((getMessageServiceMethod = MessageGrpc.getMessageServiceMethod) == null) {
      synchronized (MessageGrpc.class) {
        if ((getMessageServiceMethod = MessageGrpc.getMessageServiceMethod) == null) {
          MessageGrpc.getMessageServiceMethod = getMessageServiceMethod = 
              io.grpc.MethodDescriptor.<br.com.example.MessageOuterClass.MessageRequest, br.com.example.MessageOuterClass.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Message", "MessageService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.com.example.MessageOuterClass.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.com.example.MessageOuterClass.MessageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MessageMethodDescriptorSupplier("MessageService"))
                  .build();
          }
        }
     }
     return getMessageServiceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessageStub newStub(io.grpc.Channel channel) {
    return new MessageStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessageBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MessageBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessageFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MessageFutureStub(channel);
  }

  /**
   */
  public static abstract class MessageImplBase implements io.grpc.BindableService {

    /**
     */
    public void messageService(br.com.example.MessageOuterClass.MessageRequest request,
        io.grpc.stub.StreamObserver<br.com.example.MessageOuterClass.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMessageServiceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMessageServiceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                br.com.example.MessageOuterClass.MessageRequest,
                br.com.example.MessageOuterClass.MessageResponse>(
                  this, METHODID_MESSAGE_SERVICE)))
          .build();
    }
  }

  /**
   */
  public static final class MessageStub extends io.grpc.stub.AbstractStub<MessageStub> {
    private MessageStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageStub(channel, callOptions);
    }

    /**
     */
    public void messageService(br.com.example.MessageOuterClass.MessageRequest request,
        io.grpc.stub.StreamObserver<br.com.example.MessageOuterClass.MessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMessageServiceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MessageBlockingStub extends io.grpc.stub.AbstractStub<MessageBlockingStub> {
    private MessageBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageBlockingStub(channel, callOptions);
    }

    /**
     */
    public br.com.example.MessageOuterClass.MessageResponse messageService(br.com.example.MessageOuterClass.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getMessageServiceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessageFutureStub extends io.grpc.stub.AbstractStub<MessageFutureStub> {
    private MessageFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.com.example.MessageOuterClass.MessageResponse> messageService(
        br.com.example.MessageOuterClass.MessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMessageServiceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MESSAGE_SERVICE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessageImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MessageImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MESSAGE_SERVICE:
          serviceImpl.messageService((br.com.example.MessageOuterClass.MessageRequest) request,
              (io.grpc.stub.StreamObserver<br.com.example.MessageOuterClass.MessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MessageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MessageBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return br.com.example.MessageOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Message");
    }
  }

  private static final class MessageFileDescriptorSupplier
      extends MessageBaseDescriptorSupplier {
    MessageFileDescriptorSupplier() {}
  }

  private static final class MessageMethodDescriptorSupplier
      extends MessageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MessageMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MessageGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MessageFileDescriptorSupplier())
              .addMethod(getMessageServiceMethod())
              .build();
        }
      }
    }
    return result;
  }
}

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common_types.proto

package cm;

public final class CommonTypes {
  private CommonTypes() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code cm.PageTagType}
   */
  public enum PageTagType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>PAGE_POLITICAL_SENSITIVE = 1;</code>
     */
    PAGE_POLITICAL_SENSITIVE(0, 1),
    ;

    /**
     * <code>PAGE_POLITICAL_SENSITIVE = 1;</code>
     */
    public static final int PAGE_POLITICAL_SENSITIVE_VALUE = 1;


    public final int getNumber() { return value; }

    public static PageTagType valueOf(int value) {
      switch (value) {
        case 1: return PAGE_POLITICAL_SENSITIVE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PageTagType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<PageTagType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PageTagType>() {
            public PageTagType findValueByNumber(int number) {
              return PageTagType.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return CommonTypes.getDescriptor().getEnumTypes().get(0);
    }

    private static final PageTagType[] VALUES = values();

    public static PageTagType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private PageTagType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:cm.PageTagType)
  }

  public interface KeywordOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional int64 keyword_id = 1;
    /**
     * <code>optional int64 keyword_id = 1;</code>
     */
    boolean hasKeywordId();
    /**
     * <code>optional int64 keyword_id = 1;</code>
     */
    long getKeywordId();

    // optional double weight = 2;
    /**
     * <code>optional double weight = 2;</code>
     */
    boolean hasWeight();
    /**
     * <code>optional double weight = 2;</code>
     */
    double getWeight();

    // optional uint64 timestamp = 3;
    /**
     * <code>optional uint64 timestamp = 3;</code>
     */
    boolean hasTimestamp();
    /**
     * <code>optional uint64 timestamp = 3;</code>
     */
    long getTimestamp();

    // optional bytes text = 4;
    /**
     * <code>optional bytes text = 4;</code>
     */
    boolean hasText();
    /**
     * <code>optional bytes text = 4;</code>
     */
    com.google.protobuf.ByteString getText();
  }
  /**
   * Protobuf type {@code cm.Keyword}
   */
  public static final class Keyword extends
      com.google.protobuf.GeneratedMessage
      implements KeywordOrBuilder {
    // Use Keyword.newBuilder() to construct.
    private Keyword(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Keyword(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Keyword defaultInstance;
    public static Keyword getDefaultInstance() {
      return defaultInstance;
    }

    public Keyword getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Keyword(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              keywordId_ = input.readInt64();
              break;
            }
            case 17: {
              bitField0_ |= 0x00000002;
              weight_ = input.readDouble();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              timestamp_ = input.readUInt64();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              text_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CommonTypes.internal_static_cm_Keyword_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CommonTypes.internal_static_cm_Keyword_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Keyword.class, Builder.class);
    }

    public static com.google.protobuf.Parser<Keyword> PARSER =
        new com.google.protobuf.AbstractParser<Keyword>() {
      public Keyword parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Keyword(input, extensionRegistry);
      }
    };

    @Override
    public com.google.protobuf.Parser<Keyword> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional int64 keyword_id = 1;
    public static final int KEYWORD_ID_FIELD_NUMBER = 1;
    private long keywordId_;
    /**
     * <code>optional int64 keyword_id = 1;</code>
     */
    public boolean hasKeywordId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 keyword_id = 1;</code>
     */
    public long getKeywordId() {
      return keywordId_;
    }

    // optional double weight = 2;
    public static final int WEIGHT_FIELD_NUMBER = 2;
    private double weight_;
    /**
     * <code>optional double weight = 2;</code>
     */
    public boolean hasWeight() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional double weight = 2;</code>
     */
    public double getWeight() {
      return weight_;
    }

    // optional uint64 timestamp = 3;
    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    private long timestamp_;
    /**
     * <code>optional uint64 timestamp = 3;</code>
     */
    public boolean hasTimestamp() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional uint64 timestamp = 3;</code>
     */
    public long getTimestamp() {
      return timestamp_;
    }

    // optional bytes text = 4;
    public static final int TEXT_FIELD_NUMBER = 4;
    private com.google.protobuf.ByteString text_;
    /**
     * <code>optional bytes text = 4;</code>
     */
    public boolean hasText() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional bytes text = 4;</code>
     */
    public com.google.protobuf.ByteString getText() {
      return text_;
    }

    private void initFields() {
      keywordId_ = 0L;
      weight_ = 0D;
      timestamp_ = 0L;
      text_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, keywordId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeDouble(2, weight_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeUInt64(3, timestamp_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, text_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, keywordId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(2, weight_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(3, timestamp_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, text_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    protected Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static Keyword parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Keyword parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Keyword parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Keyword parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Keyword parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Keyword parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static Keyword parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static Keyword parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static Keyword parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Keyword parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(Keyword prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code cm.Keyword}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements KeywordOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return CommonTypes.internal_static_cm_Keyword_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return CommonTypes.internal_static_cm_Keyword_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Keyword.class, Builder.class);
      }

      // Construct using cm.CommonTypes.Keyword.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        keywordId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        weight_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000002);
        timestamp_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000004);
        text_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return CommonTypes.internal_static_cm_Keyword_descriptor;
      }

      public Keyword getDefaultInstanceForType() {
        return Keyword.getDefaultInstance();
      }

      public Keyword build() {
        Keyword result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public Keyword buildPartial() {
        Keyword result = new Keyword(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.keywordId_ = keywordId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.weight_ = weight_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.timestamp_ = timestamp_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.text_ = text_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Keyword) {
          return mergeFrom((Keyword)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Keyword other) {
        if (other == Keyword.getDefaultInstance()) return this;
        if (other.hasKeywordId()) {
          setKeywordId(other.getKeywordId());
        }
        if (other.hasWeight()) {
          setWeight(other.getWeight());
        }
        if (other.hasTimestamp()) {
          setTimestamp(other.getTimestamp());
        }
        if (other.hasText()) {
          setText(other.getText());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Keyword parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (Keyword) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional int64 keyword_id = 1;
      private long keywordId_ ;
      /**
       * <code>optional int64 keyword_id = 1;</code>
       */
      public boolean hasKeywordId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int64 keyword_id = 1;</code>
       */
      public long getKeywordId() {
        return keywordId_;
      }
      /**
       * <code>optional int64 keyword_id = 1;</code>
       */
      public Builder setKeywordId(long value) {
        bitField0_ |= 0x00000001;
        keywordId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 keyword_id = 1;</code>
       */
      public Builder clearKeywordId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        keywordId_ = 0L;
        onChanged();
        return this;
      }

      // optional double weight = 2;
      private double weight_ ;
      /**
       * <code>optional double weight = 2;</code>
       */
      public boolean hasWeight() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional double weight = 2;</code>
       */
      public double getWeight() {
        return weight_;
      }
      /**
       * <code>optional double weight = 2;</code>
       */
      public Builder setWeight(double value) {
        bitField0_ |= 0x00000002;
        weight_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional double weight = 2;</code>
       */
      public Builder clearWeight() {
        bitField0_ = (bitField0_ & ~0x00000002);
        weight_ = 0D;
        onChanged();
        return this;
      }

      // optional uint64 timestamp = 3;
      private long timestamp_ ;
      /**
       * <code>optional uint64 timestamp = 3;</code>
       */
      public boolean hasTimestamp() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional uint64 timestamp = 3;</code>
       */
      public long getTimestamp() {
        return timestamp_;
      }
      /**
       * <code>optional uint64 timestamp = 3;</code>
       */
      public Builder setTimestamp(long value) {
        bitField0_ |= 0x00000004;
        timestamp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional uint64 timestamp = 3;</code>
       */
      public Builder clearTimestamp() {
        bitField0_ = (bitField0_ & ~0x00000004);
        timestamp_ = 0L;
        onChanged();
        return this;
      }

      // optional bytes text = 4;
      private com.google.protobuf.ByteString text_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes text = 4;</code>
       */
      public boolean hasText() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional bytes text = 4;</code>
       */
      public com.google.protobuf.ByteString getText() {
        return text_;
      }
      /**
       * <code>optional bytes text = 4;</code>
       */
      public Builder setText(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        text_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes text = 4;</code>
       */
      public Builder clearText() {
        bitField0_ = (bitField0_ & ~0x00000008);
        text_ = getDefaultInstance().getText();
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:cm.Keyword)
    }

    static {
      defaultInstance = new Keyword(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:cm.Keyword)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_cm_Keyword_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cm_Keyword_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022common_types.proto\022\002cm\"N\n\007Keyword\022\022\n\nk" +
      "eyword_id\030\001 \001(\003\022\016\n\006weight\030\002 \001(\001\022\021\n\ttimes" +
      "tamp\030\003 \001(\004\022\014\n\004text\030\004 \001(\014*+\n\013PageTagType\022" +
      "\034\n\030PAGE_POLITICAL_SENSITIVE\020\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_cm_Keyword_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_cm_Keyword_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_cm_Keyword_descriptor,
              new String[] { "KeywordId", "Weight", "Timestamp", "Text", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}

/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package giros.org;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Interface extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8931742243896160581L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Interface\",\"namespace\":\"giros.org\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"doc\":\"/interfaces/interface/name\"},{\"name\":\"config\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Config\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"doc\":\"/interfaces/interface/config/name\"},{\"name\":\"type\",\"type\":[\"null\",\"string\"],\"doc\":\"/interfaces/interface/config/type\"},{\"name\":\"mtu\",\"type\":[\"null\",\"int\"],\"doc\":\"/interfaces/interface/config/mtu\"},{\"name\":\"loopback_mode\",\"type\":[\"null\",\"boolean\"],\"doc\":\"/interfaces/interface/config/loopback-mode\"},{\"name\":\"description\",\"type\":[\"null\",\"string\"],\"doc\":\"/interfaces/interface/config/description\"},{\"name\":\"enabled\",\"type\":[\"null\",\"boolean\"],\"doc\":\"/interfaces/interface/config/enabled\"}],\"aliases\":[\"configType\"]}],\"doc\":\"/interfaces/interface/config\"},{\"name\":\"state\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"State\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"doc\":\"/interfaces/interface/state/name\"},{\"name\":\"type\",\"type\":[\"null\",\"string\"],\"doc\":\"/interfaces/interface/state/type\"},{\"name\":\"mtu\",\"type\":[\"null\",\"int\"],\"doc\":\"/interfaces/interface/state/mtu\"},{\"name\":\"loopback_mode\",\"type\":[\"null\",\"boolean\"],\"doc\":\"/interfaces/interface/state/loopback-mode\"},{\"name\":\"description\",\"type\":[\"null\",\"string\"],\"doc\":\"/interfaces/interface/state/description\"},{\"name\":\"enabled\",\"type\":[\"null\",\"boolean\"],\"doc\":\"/interfaces/interface/state/enabled\"},{\"name\":\"counters\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Counters\",\"fields\":[{\"name\":\"in_octets\",\"type\":[\"null\",\"long\"],\"doc\":\"/interfaces/interface/state/counters/in-octets\"},{\"name\":\"in_pkts\",\"type\":[\"null\",\"long\"],\"doc\":\"/interfaces/interface/state/counters/in-pkts\"},{\"name\":\"in_unicast_pkts\",\"type\":[\"null\",\"long\"],\"doc\":\"/interfaces/interface/state/counters/in-unicast-pkts\"},{\"name\":\"in_multicast_pkts\",\"type\":[\"null\",\"long\"],\"doc\":\"/interfaces/interface/state/counters/in-multicast-pkts\"},{\"name\":\"out_octets\",\"type\":[\"null\",\"long\"],\"doc\":\"/interfaces/interface/state/counters/out-octets\"}],\"aliases\":[\"countersType\"]}],\"doc\":\"/interfaces/interface/state/counters\"}],\"aliases\":[\"stateType\"]}],\"doc\":\"/interfaces/interface/state\"}],\"aliases\":[\"interfaceType\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Interface> ENCODER =
      new BinaryMessageEncoder<Interface>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Interface> DECODER =
      new BinaryMessageDecoder<Interface>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Interface> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Interface> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Interface> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Interface>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Interface to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Interface from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Interface instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Interface fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** /interfaces/interface/name */
   private java.lang.CharSequence name;
  /** /interfaces/interface/config */
   private giros.org.Config config;
  /** /interfaces/interface/state */
   private giros.org.State state;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Interface() {}

  /**
   * All-args constructor.
   * @param name /interfaces/interface/name
   * @param config /interfaces/interface/config
   * @param state /interfaces/interface/state
   */
  public Interface(java.lang.CharSequence name, giros.org.Config config, giros.org.State state) {
    this.name = name;
    this.config = config;
    this.state = state;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return config;
    case 2: return state;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: config = (giros.org.Config)value$; break;
    case 2: state = (giros.org.State)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return /interfaces/interface/name
   */
  public java.lang.CharSequence getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * /interfaces/interface/name
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'config' field.
   * @return /interfaces/interface/config
   */
  public giros.org.Config getConfig() {
    return config;
  }


  /**
   * Sets the value of the 'config' field.
   * /interfaces/interface/config
   * @param value the value to set.
   */
  public void setConfig(giros.org.Config value) {
    this.config = value;
  }

  /**
   * Gets the value of the 'state' field.
   * @return /interfaces/interface/state
   */
  public giros.org.State getState() {
    return state;
  }


  /**
   * Sets the value of the 'state' field.
   * /interfaces/interface/state
   * @param value the value to set.
   */
  public void setState(giros.org.State value) {
    this.state = value;
  }

  /**
   * Creates a new Interface RecordBuilder.
   * @return A new Interface RecordBuilder
   */
  public static giros.org.Interface.Builder newBuilder() {
    return new giros.org.Interface.Builder();
  }

  /**
   * Creates a new Interface RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Interface RecordBuilder
   */
  public static giros.org.Interface.Builder newBuilder(giros.org.Interface.Builder other) {
    if (other == null) {
      return new giros.org.Interface.Builder();
    } else {
      return new giros.org.Interface.Builder(other);
    }
  }

  /**
   * Creates a new Interface RecordBuilder by copying an existing Interface instance.
   * @param other The existing instance to copy.
   * @return A new Interface RecordBuilder
   */
  public static giros.org.Interface.Builder newBuilder(giros.org.Interface other) {
    if (other == null) {
      return new giros.org.Interface.Builder();
    } else {
      return new giros.org.Interface.Builder(other);
    }
  }

  /**
   * RecordBuilder for Interface instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Interface>
    implements org.apache.avro.data.RecordBuilder<Interface> {

    /** /interfaces/interface/name */
    private java.lang.CharSequence name;
    /** /interfaces/interface/config */
    private giros.org.Config config;
    private giros.org.Config.Builder configBuilder;
    /** /interfaces/interface/state */
    private giros.org.State state;
    private giros.org.State.Builder stateBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(giros.org.Interface.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.config)) {
        this.config = data().deepCopy(fields()[1].schema(), other.config);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (other.hasConfigBuilder()) {
        this.configBuilder = giros.org.Config.newBuilder(other.getConfigBuilder());
      }
      if (isValidValue(fields()[2], other.state)) {
        this.state = data().deepCopy(fields()[2].schema(), other.state);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (other.hasStateBuilder()) {
        this.stateBuilder = giros.org.State.newBuilder(other.getStateBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing Interface instance
     * @param other The existing instance to copy.
     */
    private Builder(giros.org.Interface other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.config)) {
        this.config = data().deepCopy(fields()[1].schema(), other.config);
        fieldSetFlags()[1] = true;
      }
      this.configBuilder = null;
      if (isValidValue(fields()[2], other.state)) {
        this.state = data().deepCopy(fields()[2].schema(), other.state);
        fieldSetFlags()[2] = true;
      }
      this.stateBuilder = null;
    }

    /**
      * Gets the value of the 'name' field.
      * /interfaces/interface/name
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * /interfaces/interface/name
      * @param value The value of 'name'.
      * @return This builder.
      */
    public giros.org.Interface.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * /interfaces/interface/name
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * /interfaces/interface/name
      * @return This builder.
      */
    public giros.org.Interface.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'config' field.
      * /interfaces/interface/config
      * @return The value.
      */
    public giros.org.Config getConfig() {
      return config;
    }


    /**
      * Sets the value of the 'config' field.
      * /interfaces/interface/config
      * @param value The value of 'config'.
      * @return This builder.
      */
    public giros.org.Interface.Builder setConfig(giros.org.Config value) {
      validate(fields()[1], value);
      this.configBuilder = null;
      this.config = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'config' field has been set.
      * /interfaces/interface/config
      * @return True if the 'config' field has been set, false otherwise.
      */
    public boolean hasConfig() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'config' field and creates one if it doesn't exist yet.
     * /interfaces/interface/config
     * @return This builder.
     */
    public giros.org.Config.Builder getConfigBuilder() {
      if (configBuilder == null) {
        if (hasConfig()) {
          setConfigBuilder(giros.org.Config.newBuilder(config));
        } else {
          setConfigBuilder(giros.org.Config.newBuilder());
        }
      }
      return configBuilder;
    }

    /**
     * Sets the Builder instance for the 'config' field
     * /interfaces/interface/config
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public giros.org.Interface.Builder setConfigBuilder(giros.org.Config.Builder value) {
      clearConfig();
      configBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'config' field has an active Builder instance
     * /interfaces/interface/config
     * @return True if the 'config' field has an active Builder instance
     */
    public boolean hasConfigBuilder() {
      return configBuilder != null;
    }

    /**
      * Clears the value of the 'config' field.
      * /interfaces/interface/config
      * @return This builder.
      */
    public giros.org.Interface.Builder clearConfig() {
      config = null;
      configBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'state' field.
      * /interfaces/interface/state
      * @return The value.
      */
    public giros.org.State getState() {
      return state;
    }


    /**
      * Sets the value of the 'state' field.
      * /interfaces/interface/state
      * @param value The value of 'state'.
      * @return This builder.
      */
    public giros.org.Interface.Builder setState(giros.org.State value) {
      validate(fields()[2], value);
      this.stateBuilder = null;
      this.state = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'state' field has been set.
      * /interfaces/interface/state
      * @return True if the 'state' field has been set, false otherwise.
      */
    public boolean hasState() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'state' field and creates one if it doesn't exist yet.
     * /interfaces/interface/state
     * @return This builder.
     */
    public giros.org.State.Builder getStateBuilder() {
      if (stateBuilder == null) {
        if (hasState()) {
          setStateBuilder(giros.org.State.newBuilder(state));
        } else {
          setStateBuilder(giros.org.State.newBuilder());
        }
      }
      return stateBuilder;
    }

    /**
     * Sets the Builder instance for the 'state' field
     * /interfaces/interface/state
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public giros.org.Interface.Builder setStateBuilder(giros.org.State.Builder value) {
      clearState();
      stateBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'state' field has an active Builder instance
     * /interfaces/interface/state
     * @return True if the 'state' field has an active Builder instance
     */
    public boolean hasStateBuilder() {
      return stateBuilder != null;
    }

    /**
      * Clears the value of the 'state' field.
      * /interfaces/interface/state
      * @return This builder.
      */
    public giros.org.Interface.Builder clearState() {
      state = null;
      stateBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Interface build() {
      try {
        Interface record = new Interface();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        if (configBuilder != null) {
          try {
            record.config = this.configBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("config"));
            throw e;
          }
        } else {
          record.config = fieldSetFlags()[1] ? this.config : (giros.org.Config) defaultValue(fields()[1]);
        }
        if (stateBuilder != null) {
          try {
            record.state = this.stateBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("state"));
            throw e;
          }
        } else {
          record.state = fieldSetFlags()[2] ? this.state : (giros.org.State) defaultValue(fields()[2]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Interface>
    WRITER$ = (org.apache.avro.io.DatumWriter<Interface>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Interface>
    READER$ = (org.apache.avro.io.DatumReader<Interface>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.name == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.name);
    }

    if (this.config == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      this.config.customEncode(out);
    }

    if (this.state == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      this.state.customEncode(out);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.name = null;
      } else {
        this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.config = null;
      } else {
        if (this.config == null) {
          this.config = new giros.org.Config();
        }
        this.config.customDecode(in);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.state = null;
      } else {
        if (this.state == null) {
          this.state = new giros.org.State();
        }
        this.state.customDecode(in);
      }

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.name = null;
          } else {
            this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.config = null;
          } else {
            if (this.config == null) {
              this.config = new giros.org.Config();
            }
            this.config.customDecode(in);
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.state = null;
          } else {
            if (this.state == null) {
              this.state = new giros.org.State();
            }
            this.state.customDecode(in);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}











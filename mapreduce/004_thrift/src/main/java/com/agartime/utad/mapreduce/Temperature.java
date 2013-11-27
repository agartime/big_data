/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.agartime.utad.mapreduce;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Temperature implements org.apache.thrift.TBase<Temperature, Temperature._Fields>, java.io.Serializable, Cloneable, Comparable<Temperature> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Temperature");

  private static final org.apache.thrift.protocol.TField LOCATION_FIELD_DESC = new org.apache.thrift.protocol.TField("location", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DAY_FIELD_DESC = new org.apache.thrift.protocol.TField("day", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField TEMPERATURE_FIELD_DESC = new org.apache.thrift.protocol.TField("temperature", org.apache.thrift.protocol.TType.DOUBLE, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TemperatureStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TemperatureTupleSchemeFactory());
  }

  public String location; // required
  public int day; // required
  public double temperature; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LOCATION((short)1, "location"),
    DAY((short)2, "day"),
    TEMPERATURE((short)3, "temperature");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // LOCATION
          return LOCATION;
        case 2: // DAY
          return DAY;
        case 3: // TEMPERATURE
          return TEMPERATURE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __DAY_ISSET_ID = 0;
  private static final int __TEMPERATURE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LOCATION, new org.apache.thrift.meta_data.FieldMetaData("location", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DAY, new org.apache.thrift.meta_data.FieldMetaData("day", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TEMPERATURE, new org.apache.thrift.meta_data.FieldMetaData("temperature", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Temperature.class, metaDataMap);
  }

  public Temperature() {
  }

  public Temperature(
    String location,
    int day,
    double temperature)
  {
    this();
    this.location = location;
    this.day = day;
    setDayIsSet(true);
    this.temperature = temperature;
    setTemperatureIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Temperature(Temperature other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetLocation()) {
      this.location = other.location;
    }
    this.day = other.day;
    this.temperature = other.temperature;
  }

  public Temperature deepCopy() {
    return new Temperature(this);
  }

  @Override
  public void clear() {
    this.location = null;
    setDayIsSet(false);
    this.day = 0;
    setTemperatureIsSet(false);
    this.temperature = 0.0;
  }

  public String getLocation() {
    return this.location;
  }

  public Temperature setLocation(String location) {
    this.location = location;
    return this;
  }

  public void unsetLocation() {
    this.location = null;
  }

  /** Returns true if field location is set (has been assigned a value) and false otherwise */
  public boolean isSetLocation() {
    return this.location != null;
  }

  public void setLocationIsSet(boolean value) {
    if (!value) {
      this.location = null;
    }
  }

  public int getDay() {
    return this.day;
  }

  public Temperature setDay(int day) {
    this.day = day;
    setDayIsSet(true);
    return this;
  }

  public void unsetDay() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DAY_ISSET_ID);
  }

  /** Returns true if field day is set (has been assigned a value) and false otherwise */
  public boolean isSetDay() {
    return EncodingUtils.testBit(__isset_bitfield, __DAY_ISSET_ID);
  }

  public void setDayIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DAY_ISSET_ID, value);
  }

  public double getTemperature() {
    return this.temperature;
  }

  public Temperature setTemperature(double temperature) {
    this.temperature = temperature;
    setTemperatureIsSet(true);
    return this;
  }

  public void unsetTemperature() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TEMPERATURE_ISSET_ID);
  }

  /** Returns true if field temperature is set (has been assigned a value) and false otherwise */
  public boolean isSetTemperature() {
    return EncodingUtils.testBit(__isset_bitfield, __TEMPERATURE_ISSET_ID);
  }

  public void setTemperatureIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TEMPERATURE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case LOCATION:
      if (value == null) {
        unsetLocation();
      } else {
        setLocation((String)value);
      }
      break;

    case DAY:
      if (value == null) {
        unsetDay();
      } else {
        setDay((Integer)value);
      }
      break;

    case TEMPERATURE:
      if (value == null) {
        unsetTemperature();
      } else {
        setTemperature((Double)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LOCATION:
      return getLocation();

    case DAY:
      return Integer.valueOf(getDay());

    case TEMPERATURE:
      return Double.valueOf(getTemperature());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case LOCATION:
      return isSetLocation();
    case DAY:
      return isSetDay();
    case TEMPERATURE:
      return isSetTemperature();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Temperature)
      return this.equals((Temperature)that);
    return false;
  }

  public boolean equals(Temperature that) {
    if (that == null)
      return false;

    boolean this_present_location = true && this.isSetLocation();
    boolean that_present_location = true && that.isSetLocation();
    if (this_present_location || that_present_location) {
      if (!(this_present_location && that_present_location))
        return false;
      if (!this.location.equals(that.location))
        return false;
    }

    boolean this_present_day = true;
    boolean that_present_day = true;
    if (this_present_day || that_present_day) {
      if (!(this_present_day && that_present_day))
        return false;
      if (this.day != that.day)
        return false;
    }

    boolean this_present_temperature = true;
    boolean that_present_temperature = true;
    if (this_present_temperature || that_present_temperature) {
      if (!(this_present_temperature && that_present_temperature))
        return false;
      if (this.temperature != that.temperature)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(Temperature other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetLocation()).compareTo(other.isSetLocation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.location, other.location);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDay()).compareTo(other.isSetDay());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDay()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.day, other.day);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTemperature()).compareTo(other.isSetTemperature());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTemperature()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.temperature, other.temperature);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Temperature(");
    boolean first = true;

    sb.append("location:");
    if (this.location == null) {
      sb.append("null");
    } else {
      sb.append(this.location);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("day:");
    sb.append(this.day);
    first = false;
    if (!first) sb.append(", ");
    sb.append("temperature:");
    sb.append(this.temperature);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TemperatureStandardSchemeFactory implements SchemeFactory {
    public TemperatureStandardScheme getScheme() {
      return new TemperatureStandardScheme();
    }
  }

  private static class TemperatureStandardScheme extends StandardScheme<Temperature> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Temperature struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LOCATION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.location = iprot.readString();
              struct.setLocationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DAY
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.day = iprot.readI32();
              struct.setDayIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TEMPERATURE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.temperature = iprot.readDouble();
              struct.setTemperatureIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Temperature struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.location != null) {
        oprot.writeFieldBegin(LOCATION_FIELD_DESC);
        oprot.writeString(struct.location);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(DAY_FIELD_DESC);
      oprot.writeI32(struct.day);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TEMPERATURE_FIELD_DESC);
      oprot.writeDouble(struct.temperature);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TemperatureTupleSchemeFactory implements SchemeFactory {
    public TemperatureTupleScheme getScheme() {
      return new TemperatureTupleScheme();
    }
  }

  private static class TemperatureTupleScheme extends TupleScheme<Temperature> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Temperature struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetLocation()) {
        optionals.set(0);
      }
      if (struct.isSetDay()) {
        optionals.set(1);
      }
      if (struct.isSetTemperature()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetLocation()) {
        oprot.writeString(struct.location);
      }
      if (struct.isSetDay()) {
        oprot.writeI32(struct.day);
      }
      if (struct.isSetTemperature()) {
        oprot.writeDouble(struct.temperature);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Temperature struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.location = iprot.readString();
        struct.setLocationIsSet(true);
      }
      if (incoming.get(1)) {
        struct.day = iprot.readI32();
        struct.setDayIsSet(true);
      }
      if (incoming.get(2)) {
        struct.temperature = iprot.readDouble();
        struct.setTemperatureIsSet(true);
      }
    }
  }

}

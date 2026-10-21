package com.msa.common.event;

import com.msa.common.event.enums.EventType;

import java.math.BigInteger;

public interface Event {
  EventType getEventType();
  BigInteger getOrderId();
}

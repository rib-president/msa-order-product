package com.msa.common.event;

import com.msa.common.event.enums.EventType;
import lombok.*;

import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdatedEvent implements Event{
  private BigInteger orderId;

  @Override
  public EventType getEventType() {
    return EventType.STOCK_UPDATED;
  }
}

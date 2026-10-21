package com.msa.common.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseDto {
  private Boolean success;
}

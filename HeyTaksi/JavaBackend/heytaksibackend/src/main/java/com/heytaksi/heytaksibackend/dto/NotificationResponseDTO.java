package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationResponseDTO {
    private Long id;
    private String notificationName;
    private LocalDateTime notificationDate;
    private String notificationIssue;
    private String notificationContent;
    private Long notificationTime;
    private String notificationResult;
    private String notificationReceiverid;
    private String notificationSenderid;
}

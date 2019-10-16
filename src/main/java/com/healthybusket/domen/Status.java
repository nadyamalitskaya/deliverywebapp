package com.healthybusket.domen;

public enum Status {
    OPEN(1),
    READY_FOR_DELIVERY(2),
    ON_THE_WAY(3),
    DELIVERED(4),
    CLOSED(5);

    private Long statusId;

    Status(Long statusId) {
        this.statusId = statusId;
    }

    Status(int statusId) {
        this.statusId = (long) statusId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    static public long getUpdatedStatus(Long statusId) {
        if (statusId.equals(Status.OPEN.getStatusId())) {
            return Status.READY_FOR_DELIVERY.getStatusId();
        }
        if (statusId.equals(Status.READY_FOR_DELIVERY.getStatusId())) {
            return Status.ON_THE_WAY.getStatusId();
        }
        if (statusId.equals(Status.ON_THE_WAY.getStatusId())) {
            return Status.DELIVERED.getStatusId();
        }
        if (statusId.equals(Status.DELIVERED.getStatusId())) {
            return Status.CLOSED.getStatusId();
        } else {
            return statusId;
        }
    }
}
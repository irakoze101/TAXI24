package rw.bkg.taxi24.models;

public enum TripStatus {
    PENDING,
    ACCEPTED,
    STARTED,
    FINISHED,
    CANCELLED;

    TripStatus() {
    }

    public static TripStatus fromString(String tripStatus) {
        switch (tripStatus) {
            case "PENDING":
                return PENDING;
            case "ACCEPTED":
                return ACCEPTED;
            case "STARTED":
                return STARTED;
            case "FINISHED":
                return FINISHED;
            case "CANCELLED":
                return CANCELLED;
            default:
                return null;
        }
    }
}

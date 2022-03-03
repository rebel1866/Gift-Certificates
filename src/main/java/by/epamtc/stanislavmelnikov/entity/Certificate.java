package by.epamtc.stanislavmelnikov.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Certificate implements Serializable {
    public long serialVersionID = 5665548654546545475L;
    public int giftCertificateId;
    public String certificateName;
    public String description;
    public int price;
    public int duration;
    public LocalDateTime creationDate;
    public LocalDateTime lastUpdateTime;
    public List<Tag> tags;

    public int getGiftCertificateId() {
        return giftCertificateId;
    }

    public void setGiftCertificateId(int giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "serialVersionID=" + serialVersionID +
                ", giftCertificateId=" + giftCertificateId +
                ", certificateName='" + certificateName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", creationDate=" + creationDate +
                ", lastUpdateTime=" + lastUpdateTime +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return serialVersionID == that.serialVersionID && giftCertificateId == that.giftCertificateId && price == that.
                price && duration == that.duration && Objects.equals(certificateName, that.certificateName) && Objects.
                equals(description, that.description) && Objects.equals(creationDate, that.creationDate) && Objects.
                equals(lastUpdateTime, that.lastUpdateTime) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialVersionID, giftCertificateId, certificateName, description, price, duration,
                creationDate, lastUpdateTime, tags);
    }
}

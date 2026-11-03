package model;

import java.sql.Timestamp;

public class WorkOrder {

    private String idWo;
    private String namaWorkOrder;
    private String worker;
    private String pelanggan;
    private String noWa;
    private String estimasi;
    private String qrCode;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public WorkOrder() {
    }

    public WorkOrder(String idWo,
                     String namaWorkOrder,
                     String worker,
                     String pelanggan,
                     String noWa,
                     String estimasi,
                     String qrCode) {
        this.idWo = idWo;
        this.namaWorkOrder = namaWorkOrder;
        this.worker = worker;
        this.pelanggan = pelanggan;
        this.noWa = noWa;
        this.estimasi = estimasi;
        this.qrCode = qrCode;
    }

    public WorkOrder(String idWo,
                     String namaWorkOrder,
                     String worker,
                     String pelanggan,
                     String noWa,
                     String estimasi,
                     String qrCode,
                     Timestamp createdAt,
                     Timestamp updatedAt) {
        this(idWo, namaWorkOrder, worker, pelanggan, noWa, estimasi, qrCode);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static String formatId(int sequenceNumber) {
        return String.format("WO-%07d", sequenceNumber);
    }

    public static String generateQrCode(String idWo) {
        return "QR-" + idWo;
    }

    public String getIdWo() {
        return idWo;
    }

    public void setIdWo(String idWo) {
        this.idWo = idWo;
    }

    public String getNamaWorkOrder() {
        return namaWorkOrder;
    }

    public void setNamaWorkOrder(String namaWorkOrder) {
        this.namaWorkOrder = namaWorkOrder;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }

    public String getNoWa() {
        return noWa;
    }

    public void setNoWa(String noWa) {
        this.noWa = noWa;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "idWo='" + idWo + '\'' +
                ", namaWorkOrder='" + namaWorkOrder + '\'' +
                ", worker='" + worker + '\'' +
                ", pelanggan='" + pelanggan + '\'' +
                ", noWa='" + noWa + '\'' +
                ", estimasi='" + estimasi + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}


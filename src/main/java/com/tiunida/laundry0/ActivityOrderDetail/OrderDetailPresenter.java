package com.tiunida.laundry0.ActivityOrderDetail;

import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivityOrderDetail.events.OrderDetailEvents;
import com.tiunida.laundry0.ActivityOrderDetail.ui.OrderDetailViewMvp;
import com.tiunida.laundry0.R;

import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;

public class OrderDetailPresenter implements OrderDetailPresenterMvp {
    private OrderDetailInteractorMvp mOrderDetailInteractorMvp;
    private OrderDetailViewMvp mOrderDetailViewMvp;
    private EventBus mEventBus;
    String string2 = "0";

    public OrderDetailPresenter(OrderDetailViewMvp orderDetailViewMvp) {
        mOrderDetailViewMvp = orderDetailViewMvp;
        mOrderDetailInteractorMvp = new OrderDetailInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    @Subscribe
    public void onEventMainThread(OrderDetailEvents event) {
        switch (event.getEventType()) {
            case OrderDetailEvents.onGetDataSuccess:
                onGedDataSuccess(event.getDataJenis(), event.getDataTimeNow(), event.getDataTimeDone(), event.getDataWeight(), event.getDataPrice(), event.getDataPriceDiskon(), event.getDataDiskon(), event.getDataBandana(), event.getDataTopi(),
                        event.getDataMasker(), event.getDataKupluk(), event.getDataKrudung(), event.getDataPeci(), event.getDataKaos(), event.getDataKaosDalam(),
                        event.getDataKemeja(), event.getDataBajuMuslim(), event.getDataJaket(), event.getDataSweter(), event.getDataGamis(), event.getDataHanduk(),
                        event.getDataSarungTangan(), event.getDataSapuTangan(), event.getDataCelana(), event.getDataCelanaDalam(), event.getDataCelanaPendek(),
                        event.getDataSrung(), event.getDataCelanaOlahraga(), event.getDataRok(), event.getDataCelanaLevis(), event.getDataKaosKaki(), event.getDataJasAlmamater(),
                        event.getDataJas(), event.getDataSelimutBesar(), event.getDataSelimutKecil(), event.getDataBagCover(), event.getDataGordengKecil(), event.getDataGordengBesar(),
                        event.getDataSepatu(), event.getDataBantal(), event.getDataTasKecil(), event.getDataTasBesar(), event.getDataSpreiKecil(), event.getDataSpreiBesar(), event.getDataAccept(), event.getDataOnProses(), event.getDataDone(), event.getDataPaid(), event.getDataPaidConfirm(), event.getDataDelivered(), event.getDataDeliveredConfirm());
                break;
            case OrderDetailEvents.onGetDataError:

                break;
            case OrderDetailEvents.onUpadateDataSuccess:
                onUpdateSucces(event.getDataPaid(), event.getDataDelivered());
                break;
            case OrderDetailEvents.onUpdateDataError:
                onUpdateError(event.getErrorMessage());
                break;
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mOrderDetailViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    public void getOrderData(String order_id) {
        if (!order_id.isEmpty()) {
            mOrderDetailViewMvp.showProgress();
            mOrderDetailInteractorMvp.getOrderData(order_id);
        }
    }

    @Override
    public void onGedDataSuccess(String jenis,
                                 String timeNow, String timeDpne,
                                 String weight, String price,

                                 String priceDiskon, String diskon,
                                 String dataBandana, String dataTopi,
                                 String dataMasker, String dataKupluk,
                                 String dataKrudung, String dataPeci,

                                 String dataKaos, String dataKaosDalam,
                                 String dataKemeja, String dataBajuMuslim,
                                 String dataJaket, String dataSweter,
                                 String dataGamis, String dataHanduk,

                                 String dataSarungTangan, String dataSapuTangan,

                                 String dataCelana, String dataCelanaDalam,
                                 String dataCelanaPendek, String dataSrung,
                                 String dataCelanaOlahraga, String dataRok,
                                 String dataCelanaLevis, String dataKaosKaki,

                                 String dataJasAlmamater, String dataJas,
                                 String dataSelimutBesar, String dataSelimutKecil,
                                 String dataBagCover, String dataGordengKecil,
                                 String dataGordengBesar, String dataSepatu,
                                 String dataBantal, String dataTasKecil, String dataTasBesar,
                                 String dataSpreiKecil, String dataSpreiBesar,

                                 String dataAccept, String dataOnProses,
                                 String dataDone, String dataPaid, String dataPaidConfirm,
                                 String delivered, String deliveredConfirm) {

        //mOrderDetailViewMvp.showProgress();
        if (mOrderDetailViewMvp != null) {
            setInfo(jenis, timeNow, timeDpne, weight, price, priceDiskon, diskon);
            setIndicator(dataAccept, dataOnProses, dataDone, dataPaid, dataPaidConfirm, delivered, deliveredConfirm);
            setHeadCard(dataBandana, dataTopi, dataMasker, dataKupluk, dataKrudung, dataPeci);
            setBodyCard(dataKaos, dataKaosDalam, dataKemeja, dataBajuMuslim, dataJaket, dataSweter, dataGamis, dataHanduk);
            setHandCard(dataSarungTangan, dataSapuTangan);
            setFeetCard(dataCelana, dataCelanaDalam, dataCelanaPendek, dataSrung, dataCelanaOlahraga, dataRok, dataCelanaLevis, dataKaosKaki);
            setOtherCard(dataJasAlmamater, dataJas, dataSelimutBesar, dataSelimutKecil, dataBagCover, dataGordengKecil, dataGordengBesar, dataSepatu, dataBantal, dataTasKecil, dataTasBesar, dataSpreiKecil, dataSpreiBesar);
            setButtonPaid(dataDone, dataPaid, dataPaidConfirm);
            setButtonDeliver(delivered, deliveredConfirm);
            mOrderDetailViewMvp.hideProgress();
            mOrderDetailViewMvp.setAskAdminBtnEnable();
        }
    }

    public void setButtonPaid(String done, String paid, String paidConfirm) {
        String exist = "1";
        String empty = "";
        if (done.equals(exist) && paid.equals(empty) && paidConfirm.equals(empty)) {
            mOrderDetailViewMvp.setConfrimPaidBtnEnable();
            mOrderDetailViewMvp.setConfirmPaidBtnTxt("KONFIRMASI PEMBAYARAN");
        } else if (paid.equals(exist) && paidConfirm.equals(exist)) {
            mOrderDetailViewMvp.setConfirmPaidBtnDisable(R.drawable.btn_background_disable, R.color.abuabu3);
            mOrderDetailViewMvp.setConfirmPaidBtnTxt("SELESAI DIBAYAR");
        } else if (paid.equals(exist) && paidConfirm.equals(empty)) {
            mOrderDetailViewMvp.setConfirmPaidBtnDisable(R.drawable.btn_background_disable, R.color.abuabu3);
            mOrderDetailViewMvp.setConfirmPaidBtnTxt("MENUNGGU KONFIRMASI KURIR");
        } else {
            mOrderDetailViewMvp.setConfirmPaidBtnDisable(R.drawable.btn_background_disable_confirm_yet, R.color.biruLaut);
            mOrderDetailViewMvp.setConfirmPaidBtnTxt("KONFIRMASI PEMBAYARAN");
        }
    }

    public void setButtonDeliver(String deliver, String deliverConfirm) {
        String exist = "1";
        String empty = "";
        if (deliver.equals(exist) && deliverConfirm.equals(empty)) {
            mOrderDetailViewMvp.setConfrimDeliverBtnEnable();
            mOrderDetailViewMvp.setConfirmDeliverBtnTxt("KONFIRMASI DITERIMA");
        } else if (deliver.equals(exist) && deliverConfirm.equals(exist)) {
            mOrderDetailViewMvp.setConfirmDeliverBtnDisable(R.drawable.btn_background_disable, R.color.abuabu3);
            mOrderDetailViewMvp.setConfirmDeliverBtnTxt("SELESAI DIANTAR");
        } else {
            mOrderDetailViewMvp.setConfirmDeliverBtnDisable(R.drawable.btn_background_disable_confirm_yet, R.color.biruLaut);
            mOrderDetailViewMvp.setConfirmDeliverBtnTxt("KONFIRMASI DITERIMA");
        }
    }

    public void validateUpdatePaid(String order_id) {
        if (!order_id.isEmpty()) {
            mOrderDetailViewMvp.showProgress();
            mOrderDetailInteractorMvp.updatePaid(order_id);
        }
    }

    public void validateUpdateDeliver(String order_id) {
        if (!order_id.isEmpty()) {
            mOrderDetailViewMvp.showProgress();
            mOrderDetailInteractorMvp.updateDeliver(order_id);
        }
    }

    public void onUpdateSucces(String paid, String deliver) {
        if (mOrderDetailViewMvp != null) {
            mOrderDetailViewMvp.hideProgress();
            mOrderDetailViewMvp.showMessage("succes");
        }
    }

    public void onUpdateError(String error) {
        if (mOrderDetailViewMvp != null) {
            mOrderDetailViewMvp.showMessage(error);
        }
    }

    public void setInfo(String jenis, String timeNow, String timeDpne, String weight, String price, String priceDiskon, String diskon) {
       if (mOrderDetailViewMvp != null){
           mOrderDetailViewMvp.setLaundryType(jenis);

           mOrderDetailViewMvp.setTglPesan(timeNow);

           mOrderDetailViewMvp.setTglSelesai(timeDpne);

           mOrderDetailViewMvp.setLaundryWeight(weight);
           setPrice(weight, diskon, jenis);

           mOrderDetailViewMvp.setLaundryDiskon(diskon);
       }
    }

    public String setTotalPrice(int price, String diskon) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        int priceInt;
        int disokonInt = Integer.valueOf(diskon);
        int hasil;
        int totalPrice;
        priceInt = price;
        hasil = priceInt * disokonInt / 100;
        totalPrice = priceInt - hasil;
        return formatter.format(totalPrice);
    }

    public String setOriPrice(int price) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(price);
    }

    public void setPrice(String weight, String diskon, String jenis) {
        if (jenis.equals("Biasa")) {
            switch (weight) {
                case "1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(3500));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(3500, diskon));
                    break;
                case "1.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(3850));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(3850, diskon));
                    break;
                case "1.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(4200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(4200, diskon));
                    break;
                case "1.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(4550));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(4550, diskon));
                    break;
                case "1.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(4900));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(4900, diskon));
                    break;
                case "1.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(5250));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(5250, diskon));
                    break;
                case "1.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(5600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(5600, diskon));
                    break;
                case "1.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(5950));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(5950, diskon));
                    break;
                case "1.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(6300));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(6300, diskon));
                    break;
                case "1.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(6650));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(6650, diskon));
                    break;
                //-2---------------
                case "2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(7000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(7000, diskon));
                    break;
                case "2.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(7350));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(7350, diskon));
                    break;
                case "2.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(7700));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(7700, diskon));
                    break;
                case "2.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(8050));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(8050, diskon));
                    break;
                case "2.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(8400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(8400, diskon));
                    break;
                case "2.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(8750));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(8750, diskon));
                    break;
                case "2.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(9100));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(9100, diskon));
                    break;
                case "2.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(9450));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(9450, diskon));
                    break;
                case "2.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(9800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(9800, diskon));
                    break;
                case "2.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(10150));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(10150, diskon));
                    break;
                //-3---------------
                case "3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(10500));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(10500, diskon));
                    break;
                case "3.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(10850));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(10850, diskon));
                    break;
                case "3.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(3500));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(3500, diskon));
                    break;
                case "3.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(11200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(11200, diskon));
                    break;
                case "3.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(11550));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(11550, diskon));
                    break;
                case "3.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(11900));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(11900, diskon));
                    break;
                case "3.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(12250));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(12250, diskon));
                    break;
                case "3.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(12600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(12600, diskon));
                    break;
                case "3.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(12950));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(12950, diskon));
                    break;
                case "3.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(13300));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(13300, diskon));
                    break;
                //-4---------------
                case "4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(13650));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(13650, diskon));
                    break;
                case "4.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(14000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(14000, diskon));
                    break;
                case "4.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(14350));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(14350, diskon));
                    break;
                case "4.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(14700));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(14700, diskon));
                    break;
                case "4.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(15050));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(15050, diskon));
                    break;
                case "4.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(15400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(15400, diskon));
                    break;
                case "4.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(15750));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(15750, diskon));
                    break;
                case "4.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(16100));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(16100, diskon));
                    break;
                case "4.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(16450));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(16450, diskon));
                    break;
                case "4.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(16800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(16800, diskon));
                    break;
                //-5---------------
                case "5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(17150));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(17150, diskon));
                    break;
                case "5.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(17500));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(17500, diskon));
                    break;
                case "5.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(17850));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(17850, diskon));
                    break;
                case "5.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(18200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(18200, diskon));
                    break;
                case "5.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(18550));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(18550, diskon));
                    break;
                case "5.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(18900));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(18900, diskon));
                    break;
                case "5.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(19250));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(19250, diskon));
                    break;
                case "5.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(19600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(19600, diskon));
                    break;
                case "5.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(19950));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(19950, diskon));
                    break;
                case "5.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(20300));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(20300, diskon));
                    break;
                //-6---------------
                case "6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(20650));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(20650, diskon));
                    break;
                case "6.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(21000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(21000, diskon));
                    break;
                case "6.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(21350));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(21350, diskon));
                    break;
                case "6.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(21700));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(21700, diskon));
                    break;
                case "6.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(22050));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(22050, diskon));
                    break;
                case "6.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(22400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(22400, diskon));
                    break;
                case "6.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(22750));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(22750, diskon));
                    break;
                case "6.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(23100));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(23100, diskon));
                    break;
                case "6.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(23450));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(23450, diskon));
                    break;
                case "6.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(23800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(23800, diskon));
                    break;
                //-7---------------
                case "7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24150));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24150, diskon));
                    break;
                case "7.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24500));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24500, diskon));
                    break;
                case "7.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24850));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24850, diskon));
                    break;
                case "7.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(25200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(25200, diskon));
                    break;
                case "7.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(25550));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(25550, diskon));
                    break;
                case "7.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(25900));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(25900, diskon));
                    break;
                case "7.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(26250));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(26250, diskon));
                    break;
                case "7.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(26600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(26600, diskon));
                    break;
                case "7.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(26950));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(26950, diskon));
                    break;
                case "7.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(27300));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(27300, diskon));
                    break;
                //-8---------------
                case "8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(27650));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(27650, diskon));
                    break;
                case "8.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(28000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(28000, diskon));
                    break;
                case "8.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(28350));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(28350, diskon));
                    break;
                case "8.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(28700));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(28700, diskon));
                    break;
                case "8.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(29050));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(29050, diskon));
                    break;
                case "8.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(29400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(29400, diskon));
                    break;
                case "8.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(29750));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(29750, diskon));
                    break;
                case "8.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(30100));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(30100, diskon));
                    break;
                case "8.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(30450));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(30450, diskon));
                    break;
                case "8.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(30800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(30800, diskon));
                    break;
                //-9---------------
                case "9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(31150));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(31150, diskon));
                    break;
                case "9.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(31500));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(31500, diskon));
                    break;
                case "9.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(31850));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(31850, diskon));
                    break;
                case "9.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(32200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(32200, diskon));
                    break;
                case "9.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(32550));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(32550, diskon));
                    break;
                case "9.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(32900));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(32900, diskon));
                    break;
                case "9.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33250));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33250, diskon));
                    break;
                case "9.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33600, diskon));
                    break;
                case "9.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33950));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33950, diskon));
                    break;
                case "9.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(34300));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(34300, diskon));
                    break;
            }
        }

        if (jenis.equals("Express")) {
            switch (weight) {
                case "1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(6000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(6000, diskon));
                    break;
                case "1.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(6600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(6600, diskon));
                    break;
                case "1.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(7200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(7200, diskon));
                    break;
                case "1.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(7800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(7800, diskon));
                    break;
                case "1.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(8400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(8400, diskon));
                    break;
                case "1.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(9000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(9000, diskon));
                    break;
                case "1.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(9600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(9600, diskon));
                    break;
                case "1.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(10200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(10200, diskon));
                    break;
                case "1.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(10800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(10800, diskon));
                    break;
                case "1.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(11400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(11400, diskon));
                    break;
                    //-2---------------
                case "2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(12000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(12000, diskon));
                    break;
                case "2.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(12600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(12600, diskon));
                    break;
                case "2.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(13200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(13200, diskon));
                    break;
                case "2.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(13800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(13800, diskon));
                    break;
                case "2.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(14400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(14400, diskon));
                    break;
                case "2.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(15000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(15000, diskon));
                    break;
                case "2.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(15600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(15600, diskon));
                    break;
                case "2.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(16200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(16200, diskon));
                    break;
                case "2.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(16800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(16800, diskon));
                    break;
                case "2.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(17400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(17400, diskon));
                    break;
                    //-3---------------
                case "3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(18000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(18000, diskon));
                    break;
                case "3.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(18600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(18600, diskon));
                    break;
                case "3.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(19200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(19200, diskon));
                    break;
                case "3.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(19800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(19800, diskon));
                    break;
                case "3.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(20400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(20400, diskon));
                    break;
                case "3.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(20000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(20000, diskon));
                    break;
                case "3.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(20600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(20600, diskon));
                    break;
                case "3.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(21200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(21200, diskon));
                    break;
                case "3.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(21800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(21800, diskon));
                    break;
                case "3.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(22400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(22400, diskon));
                    break;
                //-4---------------
                case "4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(22000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(22000, diskon));
                    break;
                case "4.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(22600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(22600, diskon));
                    break;
                case "4.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(23200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(23200, diskon));
                    break;
                case "4.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(23800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(23800, diskon));
                    break;
                case "4.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24400, diskon));
                    break;
                case "4.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24000, diskon));
                    break;
                case "4.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24600, diskon));
                    break;
                case "4.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(25200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(25200, diskon));
                    break;
                case "4.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(25800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(25800, diskon));
                    break;
                case "4.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(26400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(26400, diskon));
                    break;
                //-5---------------
                case "5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(27000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(27000, diskon));
                    break;
                case "5.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(27600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(27600, diskon));
                    break;
                case "5.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(28200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(28200, diskon));
                    break;
                case "5.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(28800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(28800, diskon));
                    break;
                case "5.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(29400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(29400, diskon));
                    break;
                case "5.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(29000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(29000, diskon));
                    break;
                case "5.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(29600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(29600, diskon));
                    break;
                case "5.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(30200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(30200, diskon));
                    break;
                case "5.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(30800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(30800, diskon));
                    break;
                case "5.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(31400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(31400, diskon));
                    break;
                //-6---------------
                case "6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(31000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(31000, diskon));
                    break;
                case "6.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(31600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(31600, diskon));
                    break;
                case "6.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(32200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(32200, diskon));
                    break;
                case "6.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(32800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(32800, diskon));
                    break;
                case "6.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33400, diskon));
                    break;
                case "6.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33000, diskon));
                    break;
                case "6.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33600, diskon));
                    break;
                case "6.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(34200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(34200, diskon));
                    break;
                case "6.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(34800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(34800, diskon));
                    break;
                case "6.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(35400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(35400, diskon));
                    break;
                //-7---------------
                case "7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(36000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(36000, diskon));
                    break;
                case "7.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(36600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(36600, diskon));
                    break;
                case "7.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(37200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(37200, diskon));
                    break;
                case "7.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(37800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(37800, diskon));
                    break;
                case "7.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(38400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(38400, diskon));
                    break;
                case "7.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(38000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(38000, diskon));
                    break;
                case "7.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(38600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(38600, diskon));
                    break;
                case "7.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(39200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(39200, diskon));
                    break;
                case "7.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(39800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(39800, diskon));
                    break;
                case "7.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(40400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(40400, diskon));
                    break;
                //-8---------------
                case "8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(41000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(41000, diskon));
                    break;
                case "8.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(41600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(41600, diskon));
                    break;
                case "8.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(42200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(42200, diskon));
                    break;
                case "8.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(42800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(42800, diskon));
                    break;
                case "8.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(43400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(43400, diskon));
                    break;
                case "8.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(44000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(44000, diskon));
                    break;
                case "8.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(44600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(44600, diskon));
                    break;
                case "8.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(45200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(45200, diskon));
                    break;
                case "8.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(45800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(45800, diskon));
                    break;
                case "8.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(46400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(46400, diskon));
                    break;
                //-9---------------
                case "9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(47000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(47000, diskon));
                    break;
                case "9.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(47600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(47600, diskon));
                    break;
                case "9.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(48200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(48200, diskon));
                    break;
                case "9.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(48800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(48800, diskon));
                    break;
                case "9.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(49400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(49400, diskon));
                    break;
                case "9.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(50000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(50000, diskon));
                    break;
                case "9.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(50600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(50600, diskon));
                    break;
                case "9.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(51200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(51200, diskon));
                    break;
                case "9.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(51800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(51800, diskon));
                    break;
                case "9.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(52400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(52400, diskon));
                    break;
            }
        }

        if (jenis.equals("Kilat")) {
            switch (weight) {
                case "1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(8000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(8000, diskon));
                    break;
                case "1.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(8800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(8800, diskon));
                    break;
                case "1.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(9600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(9600, diskon));
                    break;
                case "1.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(10400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(10400, diskon));
                    break;
                case "1.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(11200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(11200, diskon));
                    break;
                case "1.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(12000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(12000, diskon));
                    break;
                case "1.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(12800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(12800, diskon));
                    break;
                case "1.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(13600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(13600, diskon));
                    break;
                case "1.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(14400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(14400, diskon));
                    break;
                case "1.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(15200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(15200, diskon));
                    break;
                    //-2---------------
                case "2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(16000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(16000, diskon));
                    break;
                case "2.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(16800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(16800, diskon));
                    break;
                case "2.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(17600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(17600, diskon));
                    break;
                case "2.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(18400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(18400, diskon));
                    break;
                case "2.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(19200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(19200, diskon));
                    break;
                case "2.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(20000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(20000, diskon));
                    break;
                case "2.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(20800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(20800, diskon));
                    break;
                case "2.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(21600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(21600, diskon));
                    break;
                case "2.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(22400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(22400, diskon));
                    break;
                case "2.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(23200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(23200, diskon));
                    break;
                //-3---------------
                case "3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24000, diskon));
                    break;
                case "3.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(24800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(24800, diskon));
                    break;
                case "3.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(25600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(25600, diskon));
                    break;
                case "3.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(26400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(26400, diskon));
                    break;
                case "3.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(27200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(27200, diskon));
                    break;
                case "3.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(28000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(28000, diskon));
                    break;
                case "3.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(28800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(28800, diskon));
                    break;
                case "3.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(29600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(29600, diskon));
                    break;
                case "3.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(30400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(30400, diskon));
                    break;
                case "3.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(31200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(31200, diskon));
                    break;
                //-4---------------
                case "4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(32000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(32000, diskon));
                    break;
                case "4.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(32800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(32800, diskon));
                    break;
                case "4.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33600, diskon));
                    break;
                case "4.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(33400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(33400, diskon));
                    break;
                case "4.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(34200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(34200, diskon));
                    break;
                case "4.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(35000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(35000, diskon));
                    break;
                case "4.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(35800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(35800, diskon));
                    break;
                case "4.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(36600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(36600, diskon));
                    break;
                case "4.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(37400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(37400, diskon));
                    break;
                case "4.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(38200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(38200, diskon));
                    break;
                //-5---------------
                case "5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(39000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(39000, diskon));
                    break;
                case "5.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(39800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(39800, diskon));
                    break;
                case "5.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(40600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(40600, diskon));
                    break;
                case "5.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(41400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(41400, diskon));
                    break;
                case "5.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(42200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(42200, diskon));
                    break;
                case "5.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(43000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(43000, diskon));
                    break;
                case "5.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(43800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(43800, diskon));
                    break;
                case "5.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(44600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(44600, diskon));
                    break;
                case "5.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(45400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(45400, diskon));
                    break;
                case "5.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(46200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(46200, diskon));
                    break;
                //-6---------------
                case "6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(47000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(47000, diskon));
                    break;
                case "6.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(47800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(47800, diskon));
                    break;
                case "6.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(48600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(48600, diskon));
                    break;
                case "6.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(49400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(49400, diskon));
                    break;
                case "6.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(50200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(50200, diskon));
                    break;
                case "6.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(51000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(51000, diskon));
                    break;
                case "6.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(51800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(51800, diskon));
                    break;
                case "6.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(52600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(52600, diskon));
                    break;
                case "6.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(53400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(53400, diskon));
                    break;
                case "6.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(54200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(54200, diskon));
                    break;
                //-7---------------
                case "7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(55000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(55000, diskon));
                    break;
                case "7.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(55800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(55800, diskon));
                    break;
                case "7.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(56600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(56600, diskon));
                    break;
                case "7.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(57400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(57400, diskon));
                    break;
                case "7.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(58200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(58200, diskon));
                    break;
                case "7.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(59000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(59000, diskon));
                    break;
                case "7.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(59800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(59800, diskon));
                    break;
                case "7.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(60600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(60600, diskon));
                    break;
                case "7.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(61400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(61400, diskon));
                    break;
                case "7.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(62200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(62200, diskon));
                    break;
                //-8---------------
                case "8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(63000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(63000, diskon));
                    break;
                case "8.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(63800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(63800, diskon));
                    break;
                case "8.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(64600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(64600, diskon));
                    break;
                case "8.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(65400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(65400, diskon));
                    break;
                case "8.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(66200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(66200, diskon));
                    break;
                case "8.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(67000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(67000, diskon));
                    break;
                case "8.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(67800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(67800, diskon));
                    break;
                case "8.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(68600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(68600, diskon));
                    break;
                case "8.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(69400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(69400, diskon));
                    break;
                case "8.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(70200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(70200, diskon));
                    break;
                //-9---------------
                case "9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(71000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(71000, diskon));
                    break;
                case "9.1":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(71800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(71800, diskon));
                    break;
                case "9.2":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(72600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(72600, diskon));
                    break;
                case "9.3":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(73400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(73400, diskon));
                    break;
                case "9.4":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(74200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(74200, diskon));
                    break;
                case "9.5":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(75000));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(75000, diskon));
                    break;
                case "9.6":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(75800));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(75800, diskon));
                    break;
                case "9.7":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(76600));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(76600, diskon));
                    break;
                case "9.8":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(77400));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(77400, diskon));
                    break;
                case "9.9":
                    mOrderDetailViewMvp.setLaundryOriginalPrice(setOriPrice(78200));
                    mOrderDetailViewMvp.setLaundryPrice(setTotalPrice(78200, diskon));
                    break;
            }
        }

    }

    public void setIndicator(String dataAccept, String dataOnProses, String dataDone, String dataPaid, String dataPaidConfirm, String delivered, String deliveredConfirm) {
        String string1 = "1";
        if (dataAccept.equals(string1)) {
            mOrderDetailViewMvp.setAcceptIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setAcceptIndicatorUnCheck();
        }

        if (dataOnProses.equals(string1)) {
            mOrderDetailViewMvp.setProsesIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setProsesIndicatorUnCheck();
        }

        if (dataDone.equals(string1)) {
            mOrderDetailViewMvp.setDoneIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setDoneIndicatorUnCheck();
        }

        if (dataPaid.equals(string1) && dataPaidConfirm.equals(string1)) {
            mOrderDetailViewMvp.setPaidIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setPaidIndicatorUnCheck();
        }

        if (delivered.equals(string1) && deliveredConfirm.equals(string1)) {
            mOrderDetailViewMvp.setDeliveredIndicatorCheck();
        } else {
            mOrderDetailViewMvp.setDeliveredIndicatorUnCheck();
        }
    }

    public void setHeadCard(String dataBandana, String dataTopi, String dataMasker, String dataKupluk, String dataKrudung, String dataPeci) {
        if (dataBandana.equals(string2)) {
            mOrderDetailViewMvp.setBandanaCardGone();
        } else {
            mOrderDetailViewMvp.setBandanaNumTxt(dataBandana);
        }

        if (dataTopi.equals(string2)) {
            mOrderDetailViewMvp.setTopiCardGone();
        } else {
            mOrderDetailViewMvp.setTopiNumTxt(dataTopi);
        }

        if (dataMasker.equals(string2)) {
            mOrderDetailViewMvp.setMaskerCardGone();
        } else {
            mOrderDetailViewMvp.setMaskerNumTxt(dataMasker);
        }

        if (dataKupluk.equals(string2)) {
            mOrderDetailViewMvp.setKuplukCardGone();
        } else {
            mOrderDetailViewMvp.setKuplukNumTxt(dataKupluk);
        }

        if (dataKrudung.equals(string2)) {
            mOrderDetailViewMvp.setKrudungCardGone();
        } else {
            mOrderDetailViewMvp.setKrudungNumTxt(dataKrudung);
        }

        if (dataPeci.equals(string2)) {
            mOrderDetailViewMvp.setPeciCardGone();
        } else {
            mOrderDetailViewMvp.setPeciNumTxt(dataPeci);
        }
    }

    public void setBodyCard(String dataKaos, String dataKaosDalam, String dataKemeja, String dataBajuMuslim, String dataJaket, String dataSweter, String dataGamis, String dataHanduk) {
        if (dataKaos.equals(string2)) {
            mOrderDetailViewMvp.setKaosCardGone();
        } else {
            mOrderDetailViewMvp.setKaosNumTxt(dataKaos);
        }

        if (dataKaosDalam.equals(string2)) {
            mOrderDetailViewMvp.setKaosDalamCardGone();
        } else {
            mOrderDetailViewMvp.setKaosDalamNumTxt(dataKaosDalam);
        }

        if (dataKemeja.equals(string2)) {
            mOrderDetailViewMvp.setKemajaCardGone();
        } else {
            mOrderDetailViewMvp.setKemejaNumTxt(dataKemeja);
        }

        if (dataBajuMuslim.equals(string2)) {
            mOrderDetailViewMvp.setBajuMuslimCardGone();
        } else {
            mOrderDetailViewMvp.setBajuMuslimNumTxt(dataBajuMuslim);
        }

        if (dataJaket.equals(string2)) {
            mOrderDetailViewMvp.setJaketCardGone();
        } else {
            mOrderDetailViewMvp.setJaketNumTxt(dataJaket);
        }

        if (dataSweter.equals(string2)) {
            mOrderDetailViewMvp.setSweterCardGone();
        } else {
            mOrderDetailViewMvp.setSweterNumTxt(dataSweter);
        }

        if (dataGamis.equals(string2)) {
            mOrderDetailViewMvp.setGamisCardGone();
        } else {
            mOrderDetailViewMvp.setGamisNumTxt(dataGamis);
        }

        if (dataHanduk.equals(string2)) {
            mOrderDetailViewMvp.setHandukCardGone();
        } else {
            mOrderDetailViewMvp.setHandukNumTxt(dataHanduk);
        }
    }

    public void setHandCard(String dataSarungTangan, String dataSapuTangan) {
        if (dataSarungTangan.equals(string2)) {
            mOrderDetailViewMvp.setSarungTanganCardGone();
        } else {
            mOrderDetailViewMvp.setSarungTanganNumTxt(dataSarungTangan);
        }

        if (dataSapuTangan.equals(string2)) {
            mOrderDetailViewMvp.setSapuTanganCardGone();
        } else {
            mOrderDetailViewMvp.setSapuTanganNumTxt(dataSapuTangan);
        }
    }

    public void setFeetCard(String dataCelana, String dataCelanaDalam, String dataCelanaPendek, String dataSrung, String dataCelanaOlahraga, String dataRok, String dataCelanaLevis, String dataKaosKaki) {
        if (dataCelana.equals(string2)) {
            mOrderDetailViewMvp.setCelanaCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaNumTxt(dataCelana);
        }

        if (dataCelanaOlahraga.equals(string2)) {
            mOrderDetailViewMvp.setCelanaOlahragaCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaOlahragaNumTxt(dataCelanaOlahraga);
        }

        if (dataCelanaDalam.equals(string2)) {
            mOrderDetailViewMvp.setCelanaDalamCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaDalamNumTxt(dataCelanaDalam);
        }

        if (dataCelanaPendek.equals(string2)) {
            mOrderDetailViewMvp.setCelanaPendekCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaPendekNumTxt(dataCelanaPendek);
        }

        if (dataSrung.equals(string2)) {
            mOrderDetailViewMvp.setSarungCardGone();
        } else {
            mOrderDetailViewMvp.setSarungNumTxt(dataSrung);
        }

        if (dataRok.equals(string2)) {
            mOrderDetailViewMvp.setRokCardGone();
        } else {
            mOrderDetailViewMvp.setRokNumTxt(dataRok);
        }

        if (dataCelanaLevis.equals(string2)) {
            mOrderDetailViewMvp.setCelanaLevisCardGone();
        } else {
            mOrderDetailViewMvp.setCelanaLevisNumTxt(dataCelanaLevis);
        }

        if (dataKaosKaki.equals(string2)) {
            mOrderDetailViewMvp.setKaosKakiCardGone();
        } else {
            mOrderDetailViewMvp.setKaosKakiNumTxt(dataKaosKaki);
        }
    }

    public void setOtherCard(String dataJasAlmamater, String dataJas, String dataSelimutBesar, String dataSelimutKecil, String dataBagCover, String dataGordengKecil, String dataGordengBesar, String dataSepatu, String dataBantal, String dataTasKecil, String dataTasBesar, String dataSpreiKecil, String dataSpreiBesar) {
        if (dataJasAlmamater.equals(string2)) {
            mOrderDetailViewMvp.setJasAlmamaterCardGone();
        } else {
            mOrderDetailViewMvp.setJasAlmamaterNumTxt(dataJasAlmamater);
        }

        if (dataJas.equals(string2)) {
            mOrderDetailViewMvp.setJasCardGone();
        } else {
            mOrderDetailViewMvp.setJasNumTxt(dataJas);
        }

        if (dataSelimutKecil.equals(string2)) {
            mOrderDetailViewMvp.setSelimutKecilCardGone();
        } else {
            mOrderDetailViewMvp.setSelimutKecilNumTxt(dataSelimutKecil);
        }

        if (dataSelimutBesar.equals(string2)) {
            mOrderDetailViewMvp.setSelimutBesarCardGone();
        } else {
            mOrderDetailViewMvp.setSelimutBesarNumTxt(dataSelimutBesar);
        }

        if (dataBagCover.equals(string2)) {
            mOrderDetailViewMvp.setBagCoverCardGone();
        } else {
            mOrderDetailViewMvp.setBagCoverNumTxt(dataBagCover);
        }

        if (dataGordengKecil.equals(string2)) {
            mOrderDetailViewMvp.setGordengKecilCardGone();
        } else {
            mOrderDetailViewMvp.setGordengKecilNumTxt(dataGordengKecil);
        }

        if (dataGordengBesar.equals(string2)) {
            mOrderDetailViewMvp.setGordengBesarCardGone();
        } else {
            mOrderDetailViewMvp.setGordengBesarNumTxt(dataGordengBesar);
        }

        if (dataSepatu.equals(string2)) {
            mOrderDetailViewMvp.setSepatuCardGone();
        } else {
            mOrderDetailViewMvp.setSepatuNumTxt(dataBagCover);
        }

        if (dataBantal.equals(string2)) {
            mOrderDetailViewMvp.setBantalCardGone();
        } else {
            mOrderDetailViewMvp.setBantalNumTxt(dataBantal);
        }

        if (dataTasKecil.equals(string2)) {
            mOrderDetailViewMvp.setTasKecilCardGone();
        } else {
            mOrderDetailViewMvp.setTaskecilNumTxt(dataBagCover);
        }

        if (dataTasBesar.equals(string2)) {
            mOrderDetailViewMvp.setTasBesarCardGone();
        } else {
            mOrderDetailViewMvp.setTasBesarNumTxt(dataTasBesar);
        }

        if (dataSpreiKecil.equals(string2)) {
            mOrderDetailViewMvp.setSpreiKecilCardGone();
        } else {
            mOrderDetailViewMvp.setSreiKecilNumTxt(dataSpreiKecil);
        }

        if (dataSpreiBesar.equals(string2)) {
            mOrderDetailViewMvp.setSpreiBesarCardGone();
        } else {
            mOrderDetailViewMvp.setSpreiBesarNumTxt(dataSpreiBesar);
        }
    }
}

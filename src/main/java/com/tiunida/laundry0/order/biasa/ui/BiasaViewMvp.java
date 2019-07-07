package com.tiunida.laundry0.order.biasa.ui;

public interface BiasaViewMvp {
    void showMessage(String message);
    void showProgress();
    void hideProgress();
    void navigateToMainScreen();
    void setRoomDormitory(String room, String dormitory);
    void getDataProfile();
    void getDoneTime();
    void getNowTime();
    void getUniqNumByTime();
    void confirmOnClick();
    void showOpsionalInfo();
    void showDialogConfirmData(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar);
    void showDialogEmptyData(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar);

    void setTextAkad1(String text);
    void setTextAkad2(String text);
    void setTextAkad3(String text);
    void setTextAkad4(String text);
    void setTextAkad5(String text);
    void setTextAkad6(String text);
    void setTextAkad7(String text);
    void setTextAkad8(String text);
    void setTextAkad9(String text);
    void setTextAkad10(String text);
    void setTextAkad11(String text);
    void setTextAkad12(String text);
    void setTextAkad13(String text);
    void setTextAkad14(String text);
    void setTextAkad15(String text);

    void setAkad1Gone();
    void setAkad2Gone();
    void setAkad3Gone();
    void setAkad4Gone();
    void setAkad5Gone();
    void setAkad6Gone();
    void setAkad7Gone();
    void setAkad8Gone();
    void setAkad9Gone();
    void setAkad10Gone();
    void setAkad11Gone();
    void setAkad12Gone();
    void setAkad13Gone();
    void setAkad14Gone();
    void setAkad15Gone();
    void setAkad1Visible();
    void setAkad2Visible();
    void setAkad3Visible();
    void setAkad4Visible();
    void setAkad5Visible();
    void setAkad6Visible();
    void setAkad7Visible();
    void setAkad8Visible();
    void setAkad9Visible();
    void setAkad10Visible();
    void setAkad11Visible();
    void setAkad12Visible();
    void setAkad13Visible();
    void setAkad14Visible();
    void setAkad15Visible();
    void enableInputs();
    void disableInputs();
    void setInputs(Boolean enabeled);

    void bandanaMinOnclick();
    void bandanaPlsOnClick();
    void topiMinOnclick();
    void topiPlsOnclick();
    void maskerMinOnClick();
    void maskerPlsOnClick();
    void kuplukMinOnClick();
    void kuplukPlsOnClick();
    void krudungMinOnClick();
    void krudungPlsOnClick();
    void peciMinOnClick();
    void peciPlsOnclick();

    void kaosMinOnClick();
    void kaosPlsOnClick();
    void kaosDalamOnClick();
    void kaosDalamPlsOnClick();
    void kemejaMinOnClick();
    void kemejaPlsOnClick();
    void bajuMuslimOnClick();
    void bajuMuslimPlsOnClick();
    void jaketMinOnClick();
    void jaketPlsOnClick();
    void sweterMinOnClick();
    void sweterPlsOnClick();
    void gamisMinOnClick();
    void gamisPlsOnClick();
    void handukMinOnClick();
    void handukPlsOnClick();

    void sarungTanganMinOnClick();
    void sarungTanganPlsOnClick();
    void sapuTanganMinOnClick();
    void sapuTanganPlsOnClick();

    void cenaMinOnClick();
    void cenalaPlsOnClick();
    void celanaDalamMinOnClick();
    void celanaDalamPlsOnClick();
    void celanaPendekMinOnClick();
    void celanaPendekOnClick();
    void sarungMInOnClick();
    void sarungPlsOnClick();
    void celanaOlahragaMinOnClick();
    void celanaOlahragaPlsOnClick();
    void rokMinOnClick();
    void rokPlsOnClick();
    void celanaLevisMinOnClick();
    void celanaLevisPlsOnClick();
    void kaosKakiMinOnClick();
    void kaosKakiPlsOnClick();

    void jasAlmamaterMinOnClick();
    void jasAlmamaterPlsOnClick();
    void jasMinOnClick();
    void jasPlsOnClick();
    void selimutKecilMinOnClick();
    void selimutKecilPlsOnClick();
    void selimutBesarMinOnClick();
    void selimutBesarPlsOnClick();
    void bagCoverMinOnClick();
    void bagCoverPlsOnClick();
    void gordengKecilMinOnClick();
    void gordengKecilPlsOnClick();
    void gordengBesarMinOnClick();
    void gordengBesarPlsOnClick();
    void sepatuMinOnClick();
    void sepatuPlsOnClick();
    void bantalMinOnClick();
    void bantalPlsOnClick();
    void tasKecilMinOnClick();
    void tasKecilPlsOnClick();
    void tasBesarMinOnClick();
    void tasBesarPlsOnClick();
    void spreiKecilMinOnClick();
    void spreiKecilPlsOnClick();
    void spreiBesarMinOnClick();
    void spreiBesarPlsOnClick();
}

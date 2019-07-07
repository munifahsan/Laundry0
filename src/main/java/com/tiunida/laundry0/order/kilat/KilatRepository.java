package com.tiunida.laundry0.order.kilat;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.order.kilat.events.KilatEvents;

import java.util.HashMap;
import java.util.Map;

public class KilatRepository implements KilatRepositoryMvp {
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;

    public KilatRepository() {
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getProfileData() {
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("repo onComplete suc:", "masuk");

                    if (task.getResult().exists()) {
                        String room = task.getResult().getString("3 room");
                        String dormitory = task.getResult().getString("2 dormitory");
                        String test = task.getResult().getString("done");

                        Log.d("dona data ", "" + test);

                        postEvent(KilatEvents.onGetDataSuccess, null, dormitory, room);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(KilatEvents.onGetDataError, errorMessage);
                }
            }
        });
    }

    @Override
    public void getAkadData() {
        firebaseFirestore.collection("Tentang").document("BnFBK532PL2N6KNiJIpo")
                .collection("akad").document("awYiYQq1ROA8IcE2V6mx").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("repo onComplete suc:", "masuk");

                    if (task.getResult().exists()) {
                        String akad1 = task.getResult().getString("1");
                        String akad2 = task.getResult().getString("2");
                        String akad3 = task.getResult().getString("3");
                        String akad4 = task.getResult().getString("4");
                        String akad5 = task.getResult().getString("5");
                        String akad6 = task.getResult().getString("6");
                        String akad7 = task.getResult().getString("7");
                        String akad8 = task.getResult().getString("8");
                        String akad9 = task.getResult().getString("9");
                        String akad10 = task.getResult().getString("10");
                        String akad11 = task.getResult().getString("11");
                        String akad12 = task.getResult().getString("12");
                        String akad13 = task.getResult().getString("13");
                        String akad14 = task.getResult().getString("14");
                        String akad15 = task.getResult().getString("15");

                        postEvent(KilatEvents.onGetDataSuccess, null, akad1, akad2, akad3, akad4, akad5, akad6, akad7, akad8, akad9, akad10, akad11, akad12, akad13, akad14, akad15);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(KilatEvents.onGetDataError, errorMessage);
                }
            }
        });
    }

    @Override
    public void storeFirestore(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal,
                               String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        int uniqTimeId = Integer.valueOf(uniqId);
        Map<String, Object> userMap2 = new HashMap<>();
        userMap2.put("a_user_id", user_id);
        userMap2.put("a_catatan", desc);
        userMap2.put("a_uniq_id", uniqTimeId);
        userMap2.put("a_time", time);
        userMap2.put("a_jenis", "Kilat");
        userMap2.put("a_waktu_selesai", timeDone);
        userMap2.put("a_weight", "--");
        userMap2.put("a_price2", "0");
        userMap2.put("a_price_diskon", "0");
        userMap2.put("a_diskon", "0");

        userMap2.put("b_bandana", bandana);
        userMap2.put("b_topi", topi);
        userMap2.put("b_masker", masker);
        userMap2.put("b_kupluk", kupluk);
        userMap2.put("b_krudung", krudung);
        userMap2.put("b_peci", peci);

        userMap2.put("c_kaos", kaos);
        userMap2.put("c_kaos_dalam", kaos_dalam);
        userMap2.put("c_kemeja", kemeja);
        userMap2.put("c_baju_muslim", baju_muslim);
        userMap2.put("c_jaket", jaket);
        userMap2.put("c_sweter", sweter);
        userMap2.put("c_gamis", gamis);
        userMap2.put("c_handuk", handuk);

        userMap2.put("d_sarung_tangan", sarung_tangan);
        userMap2.put("d_sapu_tangan", sapu_tangan);

        userMap2.put("f_celana", celana);
        userMap2.put("f_celana_dalam", celana_dalam);
        userMap2.put("f_celana_pendek", celana_pendek);
        userMap2.put("f_sarung", sarung);
        userMap2.put("f_celana_olahraga", celana_olahraga);
        userMap2.put("f_rok", rok);
        userMap2.put("f_celana_evis", celana_levis);
        userMap2.put("f_kaos_kaki", kaos_kaki);

        userMap2.put("g_jas_almamater", jas_almamater);
        userMap2.put("g_jas", jas);
        userMap2.put("g_selimut_kecil", selimut_kecil);
        userMap2.put("g_selimut_besar", selimut_besar);
        userMap2.put("g_bag_cover", bag_cover);
        userMap2.put("g_gordeng_kecil", gordeng_kecil);
        userMap2.put("g_gordeng_besar", gordeng_besar);
        userMap2.put("g_sepatu", sepatu);
        userMap2.put("g_bantal", bantal);
        userMap2.put("g_tas_kecil", tas_kecil);
        userMap2.put("g_tas_besar", tas_besar);
        userMap2.put("g_sprei_kecil", sprei_kecil);
        userMap2.put("g_sprei_besar", sprei_besar);

        userMap2.put("h_accepted2", "");
        userMap2.put("h_on_proses2", "");
        userMap2.put("h_done2", "");
        userMap2.put("h_paid2", "");
        userMap2.put("h_delivered2", "");

        firebaseFirestore.collection("Orders").add(userMap2).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Log.d("masuk isSuccess ", " ya");
                    postEvent(KilatEvents.onInputSuccess);
                } else {
                    String errorMessage = task.getException().getMessage();
                    postEvent(KilatEvents.onInputError, errorMessage);
                    Log.d("error nya ", "" + errorMessage);
                }
            }
        });
    }

    @Override
    public void postEvent(int type, String errorMessage, String dataRoom, String dataDormitory) {
        KilatEvents kilatEvents = new KilatEvents();
        kilatEvents.setEventType(type);
        Log.d("masuk post", "masuk post event succes not null");
        if (errorMessage == null) {
            kilatEvents.setErrorMessage(errorMessage);
        }
        kilatEvents.setDataRoom(dataRoom);
        kilatEvents.setDataDormitory(dataDormitory);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(kilatEvents);
    }

    @Override
    public void postEvent(int type, String errorMessage, String akad1, String akad2, String akad3, String akad4, String akad5, String akad6, String akad7, String akad8, String akad9, String akad10, String akad11, String akad12, String akad13, String akad14, String akad15) {
        KilatEvents kilatEvents = new KilatEvents();
        kilatEvents.setEventType(type);
        Log.d("masuk post", "masuk post event succes not null");
        if (errorMessage == null) {
            kilatEvents.setErrorMessage(errorMessage);
        }

        kilatEvents.setAkad1(akad1);
        kilatEvents.setAkad2(akad2);
        kilatEvents.setAkad3(akad3);
        kilatEvents.setAkad4(akad4);
        kilatEvents.setAkad5(akad5);
        kilatEvents.setAkad6(akad6);
        kilatEvents.setAkad7(akad7);
        kilatEvents.setAkad8(akad8);
        kilatEvents.setAkad9(akad9);
        kilatEvents.setAkad10(akad10);
        kilatEvents.setAkad11(akad11);
        kilatEvents.setAkad12(akad12);
        kilatEvents.setAkad13(akad13);
        kilatEvents.setAkad14(akad14);
        kilatEvents.setAkad15(akad15);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(kilatEvents);
    }

    @Override
    public void postEvent(int type, String errorMessage) {
        postEvent(type, errorMessage, null, null);
    }

    @Override
    public void postEvent(int type) {
        postEvent(type, null, null, null);
    }
}
package com.fuuuu.questunfucker;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainModule implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.oculus.systemux")) return;

        try {
            XposedHelpers.findAndHookMethod(
                    "X.2Z1",
                    lpparam.classLoader,
                    "A0F",
                    String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            String flagName = (String) param.args[0];
                            if ("oculus_notifications:vr_notif_feed_clear_all_enabled".equals(flagName)) {
                                param.setResult(true);
                            }
                        }
                    }
            );
        } catch (Throwable t) {
            // Hook failed silently - class or method not found
        }
    }
}
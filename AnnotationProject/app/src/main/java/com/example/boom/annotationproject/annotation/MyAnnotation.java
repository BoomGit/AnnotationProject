package com.example.boom.annotationproject.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Boom on 2016/11/21.
 */

public class MyAnnotation {

public static void init(Activity activity){
    setBindIdWithViewAnnotation(activity);
    getOnClick(activity);
}
private static void setBindIdWithViewAnnotation(Activity activity){
    Class clazz=activity.getClass();
    Field [] fields=clazz.getDeclaredFields();
    for(Field field : fields){
    BindId bindId=field.getAnnotation(BindId.class);
        if (bindId!=null){
            int Id=bindId.value();
            try {
                field.setAccessible(true);
                field.set(activity,activity.findViewById(Id));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
    private static  void getOnClick(final Activity activity){
        Class clazz=activity.getClass();
        Method [] methods =clazz.getDeclaredMethods();
        for(final Method method : methods){
            OnClick onclick =method.getAnnotation(OnClick.class);
            if (onclick!=null && onclick.value()!=0){
                View v= activity.findViewById(onclick.value());
                if (v==null){
                    return;
                }
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.setAccessible(true);
                            method.invoke(activity,v);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}

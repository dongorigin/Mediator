package cn.dong.mediator.wrapper;

import androidx.annotation.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhaodong on 2020/07/01.
 */
public class WrapperFactory {

    public static <T> T create(Class<T> typeClass, ActualWrapper<T> wrapper) {
        Object proxy = Proxy.newProxyInstance(typeClass.getClassLoader(), new Class[]{typeClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        final T actul = wrapper.actual;
                        if (actul == null) {
                            return null;
                        }
                        for (Method method1 : actul.getClass().getMethods()) {
                            if (method1.equals(method)) {
                                return method1.invoke(actul, args);
                            }
                        }
                        return null;
                    }
                });
        return (T) proxy;
    }

    public static class ActualWrapper<T> {
        @Nullable
        public T actual;
    }
}

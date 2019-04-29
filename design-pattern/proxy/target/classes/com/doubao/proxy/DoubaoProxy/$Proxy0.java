package com.doubao.proxy.DoubaoProxy;	
import com.doubao.proxy.DoubaoProxy.DoubaoInvocationHandler;	
import java.lang.reflect.*;	
public class $Proxy0 implements com.doubao.proxy.DoubaoProxy.Car{	
DoubaoInvocationHandler h;	
public $Proxy0(DoubaoInvocationHandler h) { 	
this.h = h;}	
public java.lang.String getName() {	
try{	
Method m = com.doubao.proxy.DoubaoProxy.Car.class.getMethod("getName",new Class[]{});	
return (java.lang.String)this.h.invoke(this,m,new Object[]{});	
}catch(Error _ex) { }catch(Throwable e){	
throw new UndeclaredThrowableException(e);	
}return null;}}	

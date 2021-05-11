package org.trent.jcamp.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;

/**
 * XClass类加载器
 */
public class HelloXClassLoader extends ClassLoader {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String resourceName;

    public HelloXClassLoader(String resourceName) {
        this.resourceName = resourceName;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] decode = decode(load());
        if(decode != null){
            return defineClass(name,decode,0,decode.length);
        }
        else{
            throw new ClassNotFoundException(" fail to load class " + name);
        }
    }

    private byte[] decode(byte[] source) {
        if (source != null && source.length > 0){
            byte[] decode = new byte[source.length];
            for(int i = 0;i<source.length;i++){
                decode[i] = (byte)(255-source[i]);
            }
            return decode;
        }
        else{
            return null;
        }
    }

    private byte[] load() {
        if (resourceName == null) {
            throw new IllegalArgumentException("missing resource name, please set it when HelloXClassLoader init");
        }
        ClassPathResource classPathResource = new ClassPathResource(resourceName);
        try (InputStream is = classPathResource.getInputStream(); BufferedInputStream bufferIs = new BufferedInputStream(is)) {
            return bufferIs.readAllBytes();
        } catch (IOException e) {
            logger.error("fail to load resource {} ", resourceName,e);
        }
        return null;
    }
}

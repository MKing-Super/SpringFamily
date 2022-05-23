package pers.mk.opspace.service.impl;

import pers.mk.opspace.service.Coder;

/**
 * @author MK
 */
public class JavaCoder implements Coder {
    @Override
    public void coding() {
        boolean inICU = false;
        while (!inICU){
            System.out.println("Ctrl+C");
            System.out.println("Ctrl+V");
        }
    }
}

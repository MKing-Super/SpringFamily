package pers.mk.opspace.api;

import pers.mk.opspace.api.model.Person;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.api
 * @Date 2023/3/17 16:40
 * @Version 1.0
 */
@FunctionalInterface
public interface NameChecker {

    boolean check(Person person);
}

package fr.bowserf.testdagger2;

import javax.inject.Scope;

@Scope
public @interface GenericScope {
    Class component();
}

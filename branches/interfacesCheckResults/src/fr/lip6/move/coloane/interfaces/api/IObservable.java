package fr.lip6.move.coloane.interfaces.api;

import java.util.Observer;

/**
 * Interface dedicated to observable objects.
 * 
 * @author Jean-Baptiste Voron
 */
interface IObservable {
    void addObserver(Observer o);
}
